package cobrar_factura_paciente;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import persistencia.proxy.EstadoFacturaPaciente;
import persistencia.proxy.CostoPrestacion;
import persistencia.proxy.FacturaPaciente;
import persistencia.FachadaPersistencia;
import persistencia.proxy.CostoServicio;
import persistencia.proxy.DetalleFicha;
import persistencia.criterios.Criterio;
import persistencia.proxy.Recibo;
import util.ServiciosTiempo;
import dtos.*;

public class ExpertoCobrarFacturaPaciente {
   public List<DTOFacturaPaciente> buscarFacturasPendientes() {
      // se declaran las variables a utilizar
      List<DTOFacturaPaciente> listaDtoFacturas = new ArrayList<DTOFacturaPaciente>();
      DTOFacturaPaciente dtoFactura;
      List<DetalleFicha> listaDetalle;
      List<DTODetalleServicio> listaDtoDetalle;
      DTODetalleServicio dtoDetalle;
      CostoPrestacion costoPres;
      CostoServicio costoServ;

      // se declara y crea un lista de criterios para ser utilizada en las búsquedas
      List<Criterio> criterios = new ArrayList<Criterio>();
      // se declara y crea un criterio
      Criterio criterio = FachadaPersistencia.getInstancia().getCriterio("nombreEstado", "=", "Emitida", "");
      // se agrega el criterio a la lista de criterios
      criterios.add(criterio);
      
      // busca es estado, para la factura, con el nombre "Emitida"
      List<EstadoFacturaPaciente> listaEstadosFacturas = FachadaPersistencia.getInstancia().buscar("EstadoFacturaPaciente", criterios);
      
      // se comprueba que exista el estado buscado
      if(!listaEstadosFacturas.isEmpty()){
         // se tome el primer elemento de la lista de estados de las facturas
         EstadoFacturaPaciente estadoFactura = listaEstadosFacturas.get(0);
         
         // se crea un nueva lista de criterios
         criterios = new ArrayList<Criterio>();
         criterio = FachadaPersistencia.getInstancia().getCriterio("estadoFacturaPaciente", "=", estadoFactura, "");
         criterios.add(criterio);
         
         // se buscan las facturas que no hayan sido facturadas aún
         List<FacturaPaciente> listaFacturas = FachadaPersistencia.getInstancia().buscar("FacturaPaciente", criterios);
         
         // por cada factura en la lista de facturas obtenida se crea un DTO de factura
         for(FacturaPaciente f : listaFacturas){
            dtoFactura = new DTOFacturaPaciente();
            // se le asignan los datos al DTO de factura desde la factura
            dtoFactura.setNumFactura(f.getNumFactura());
            dtoFactura.setFecha(f.getFechaEmision());
            dtoFactura.setNroFicha(f.getFichaInternacion().getNroFicha());
            dtoFactura.setNombrePaciente(f.getFichaInternacion().getPaciente().getNombre());
            dtoFactura.setNombrePrestacion(f.getFichaInternacion().getPrestacion().getDescripcion());
            
            // se crea una nueva lista de criterios
            criterios = new ArrayList<Criterio>();
            criterio = FachadaPersistencia.getInstancia().getCriterio("fechaInicio", "<=", ServiciosTiempo.getInstancia().dateToString(dtoFactura.getFecha()), "AND");
            criterios.add(criterio);

            criterio = FachadaPersistencia.getInstancia().getCriterio("fechaFin", ">=", ServiciosTiempo.getInstancia().dateToString(dtoFactura.getFecha()), "AND");
            criterios.add(criterio);

            criterio = FachadaPersistencia.getInstancia().getCriterio("prestacion", "=", f.getFichaInternacion().getPrestacion(), "");
            criterios.add(criterio);

            // se obtiene el costo de la prestación perteneciente al período indicado
            List<CostoPrestacion> listaCostosPres = FachadaPersistencia.getInstancia().buscar("CostoPrestacion", criterios);
            
            // se comprueba que se haya encontrado el costo
            if(!listaCostosPres.isEmpty()){
               // se tome el primer elemento de la lista de costos de la prestación
               costoPres = listaCostosPres.get(0);
               // se asigna al DTO de factura el costo de la prestación
               dtoFactura.setCostoPrestacion(costoPres.getMonto());
            } // fin de if de comprobación de existencia del costo
            
            // se toma la lista de detalles de la ficha de internación
            listaDetalle = f.getFichaInternacion().getDetalleFicha();
            
            // se crea la lista de DTOs de detalles
            listaDtoDetalle = new ArrayList<DTODetalleServicio>();
            
            // se toma cada detalle de la ficha de la lista
            for(DetalleFicha df : listaDetalle){
               // se crea un nuevo DTO de detalle
               dtoDetalle = new DTODetalleServicio();
               
               // se crea una nueva lista de criterios
               criterios = new ArrayList<Criterio>();
               criterio = FachadaPersistencia.getInstancia().getCriterio("fechaInicio", "<=", ServiciosTiempo.getInstancia().dateToString(dtoFactura.getFecha()), "AND");
               criterios.add(criterio);
               
               criterio = FachadaPersistencia.getInstancia().getCriterio("fechaFin", ">=", ServiciosTiempo.getInstancia().dateToString(dtoFactura.getFecha()), "AND");
               criterios.add(criterio);
               
               criterio = FachadaPersistencia.getInstancia().getCriterio("servicioEspecial", "=", df.getServicioEspecial(), "");
               criterios.add(criterio);
               
               // se busca el costo el servicio perteneciente al período indicado
               List<CostoServicio> listaCostosServ = FachadaPersistencia.getInstancia().buscar("CostoServicio", criterios);
               
               if(!listaCostosServ.isEmpty()){
                  // se tome el primer elemento de la lista de costos del servicio
                  costoServ = listaCostosServ.get(0);
                  
                  // se asignan al DTO de detalle los datos correspondientes
                  dtoDetalle.setNombreServicio(costoServ.getServicioEspecial().getNombreServicio());
                  dtoDetalle.setMonto(costoServ.getMonto());
                  dtoDetalle.setCantidad(df.getCantidad());
                  dtoDetalle.setSubtotal(dtoDetalle.getMonto() * dtoDetalle.getCantidad());
               } // fin de comprobación de existencia del costo del servicio
               
               // se agrega el DTO de detalle a la lista de DTOs de detalles
               listaDtoDetalle.add(dtoDetalle);
            } // fin de for de creación de DTOs de detalle de la ficha
            
            // se asigna al DTO de factura el monto de la factura
            dtoFactura.setMonto(f.getMonto());
            // se asigna al DTO de factura la lista de DTOs de detalles
            dtoFactura.setDtoDetalle(listaDtoDetalle);
            
            // se agrega el DTO de factura a la lista de DTOs de facturas
            listaDtoFacturas.add(dtoFactura);
         } // fin de for de creación de DTOs de facturas
      } // fin de if de búsqueda del estado de la factura
      
      // se devuelve la lista de DTOs de facturas
      return listaDtoFacturas;
   } // fin del método buscarFacturasPendientes
   
   public DTORecibo cobrarFactura(int numFactura){
      // se declara el DTO de recibo
      DTORecibo dtoRecibo = null;
      
      // se declara y crea un lista de criterios para ser utilizada en las búsquedas
      List<Criterio> criterios = new ArrayList<Criterio>();
      // se declara y crea un criterio
      Criterio criterio = FachadaPersistencia.getInstancia().getCriterio("numFactura", "=", numFactura, "");
      // se agrega el criterio a la lista de criterios
      criterios.add(criterio);
      
      // busca la factura con el número indicado
      List<FacturaPaciente> listaFacturas = FachadaPersistencia.getInstancia().buscar("FacturaPaciente", criterios);
      
      // se comprueba que exista la factura buscada
      if(!listaFacturas.isEmpty()){
         // se tome el primer elemento de la lista de facturas
         FacturaPaciente factura = listaFacturas.get(0);
         
         // comprueba si efectivamente la factura aún no se ha cobrado
         if(!factura.getEstadoFacturaPaciente().getNombreEstado().equals("Pagada")){
            // se crea un nuevo recibo y se guardan en él los datos correspondientes
            Recibo recibo = (Recibo) FachadaPersistencia.getInstancia().nuevaEntidad("Recibo");
            recibo.setFacturaPaciente(factura);
            recibo.setFecha(new Date());
            recibo.setNroRecibo(numFactura);

            // se crea el DTO de recibo y se le asignan los datos correspondientes
            dtoRecibo = new DTORecibo();
            dtoRecibo.setNroRecibo(recibo.getNroRecibo());
            dtoRecibo.setNumFactura(recibo.getFacturaPaciente().getNumFactura());
            dtoRecibo.setFecha(recibo.getFecha());
            dtoRecibo.setMonto(factura.getMonto());

            // se crea una nueva lista de criterios
            criterios = new ArrayList<Criterio>();
            criterio = FachadaPersistencia.getInstancia().getCriterio("nombreEstado", "=", "Pagada", "");
            criterios.add(criterio);

            // se busca el estado, para la factura, con el nombre "Pagada"
            List<EstadoFacturaPaciente> listaEstadosFacturas = FachadaPersistencia.getInstancia().buscar("EstadoFacturaPaciente", criterios);

            // se comprueba que exista el estado de la factura buscado
            if(!listaEstadosFacturas.isEmpty()){
               // se tome el primer elemento de la lista de estados de facturas
               EstadoFacturaPaciente estadoFactura = listaEstadosFacturas.get(0);

               // se cambia el estado de la factura por el nuevo estado encontrado
               factura.setEstadoFacturaPaciente(estadoFactura);
            } // fin de if de comprobación de existencia del estado de la factura

            // se guarda la factura
            FachadaPersistencia.getInstancia().guardar("FacturaPaciente", factura);
            // se guarda el recibo
            FachadaPersistencia.getInstancia().guardar("Recibo", recibo);
         } // fin de if de comprobación del estado de la factura
      } // fin de if de comprobación de existencia de la factura
      
      // se devuelve el DTO de recibo, o null si la factura no se encontró
      return dtoRecibo;
   } // fin del método cobrarFactura
} // fin de la clase ExpertoCobrarFacturaPaciente