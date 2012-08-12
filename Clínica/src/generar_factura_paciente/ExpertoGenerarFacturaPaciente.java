package generar_factura_paciente;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import observador.SuscriptorGenerarFacturaPaciente;
import persistencia.proxy.EstadoFichaInternacion;
import persistencia.proxy.EstadoFacturaPaciente;
import persistencia.proxy.FichaInternacion;
import persistencia.proxy.CostoPrestacion;
import persistencia.proxy.FacturaPaciente;
import persistencia.proxy.CostoServicio;
import persistencia.FachadaPersistencia;
import persistencia.criterios.Criterio;
import persistencia.proxy.DetalleFicha;
import persistencia.proxy.EstadoCama;
import persistencia.proxy.Convenio;
import dtos.DTOFichaInternacion;
import dtos.DTODetalleServicio;
import dtos.DTOFacturaPaciente;
import util.ServiciosTiempo;

public class ExpertoGenerarFacturaPaciente {
   private FichaInternacion fichaInternacion;
   private DTOFichaInternacion dtoFicha;
   
   public List<DTOFichaInternacion> buscarFichasPendientes(){
      List<DTOFichaInternacion> listaDtoFichas = new ArrayList<DTOFichaInternacion>();
      DTOFichaInternacion dtoF;
      // se declara y crea una lista de criterios para usar en la búsqueda
      List<Criterio> criterios = new ArrayList<Criterio>();
      // se daclara y crea un criterio
      Criterio criterio = FachadaPersistencia.getInstancia().getCriterio("nombreEstado", "=", "Creada", "");
      // se agrega el criterio a la lista
      criterios.add(criterio);
      
      // se busca el estado, para las fichas de internación, con nombre "Creada"
      List<EstadoFichaInternacion> listaEstadosFichas = FachadaPersistencia.getInstancia().buscar("EstadoFichaInternacion", criterios);
      
      // se comprueba que la lista no esté vacía
      if(!listaEstadosFichas.isEmpty()){
         // se obtiene el primer elemento de la lista
         EstadoFichaInternacion estadoFicha = listaEstadosFichas.get(0);
         
         // se crea una nueva lista de criterios
         criterios = new ArrayList<Criterio>();
         criterio = FachadaPersistencia.getInstancia().getCriterio("estadoFichaInternacion", "=", estadoFicha, "");
         criterios.add(criterio);
         
         // se buscan las fichas de internación que no estén facturadas
         List<FichaInternacion> listaFichas = FachadaPersistencia.getInstancia().buscar("FichaInternacion", criterios);
         
         // se recorre la lista de fichas
         for(FichaInternacion ficha : listaFichas){
            // se crea un nuevo DTO de ficha y se le asignan los datos correspondientes
            dtoF = new DTOFichaInternacion();
            dtoF.setNroFicha(ficha.getNroFicha());
            dtoF.setFecha(ficha.getFechaCreacion());
            dtoF.setNombrePaciente(ficha.getPaciente().getNombre());
            dtoF.setNombrePrestacion(ficha.getPrestacion().getDescripcion());
            
            // se agrega el DTO de ficha a la lista de DTOs de fichas
            listaDtoFichas.add(dtoF);
         } // fin de for de creación de DTOs de fichas de internación
      } // fin de if de comprobación de existencia del estado
      
      return listaDtoFichas;
   } // fin del método buscarFichasPendientes

   public DTOFichaInternacion buscarFichaInternacion(int numFicha){
      this.fichaInternacion = null;
      this.dtoFicha = null;
      // se declara y crea una lista de criterios para usar en la búsqueda
      List<Criterio> criterios = new ArrayList<Criterio>();
      // se daclara y crea un criterio
      Criterio criterio = FachadaPersistencia.getInstancia().getCriterio("nroFicha", "=", numFicha, "");
      // se agrega el criterio a la lista
      criterios.add(criterio);
      
      // se busca la ficha de internación que corresponde al número de ficha ingresado
      List<FichaInternacion> listaFichas = FachadaPersistencia.getInstancia().buscar("FichaInternacion", criterios);
      
      // se comprueba que la lista no esté vacía
      if(!listaFichas.isEmpty()){
         // se obtiene el primer elemento de la lista
         this.fichaInternacion = listaFichas.get(0);
         
         // se comprueba que la ficha de internación no esté facturada
         if(this.fichaInternacion.getEstadoFichaInternacion().getNombreEstado().equals("Creada")){
            // se crea el DTO donde se colocarán los datos de la ficha, la preatación y los servicios especiales
            this.dtoFicha = new DTOFichaInternacion();
            this.dtoFicha.setFecha(this.fichaInternacion.getFechaCreacion());
            this.dtoFicha.setNroFicha(this.fichaInternacion.getNroFicha());
            this.dtoFicha.setNumPaciente(this.fichaInternacion.getPaciente().getNumPaciente());
            this.dtoFicha.setNombrePaciente(this.fichaInternacion.getPaciente().getNombre());
            this.dtoFicha.setNombrePrestacion(this.fichaInternacion.getPrestacion().getDescripcion());
            
            // se crea una nueva lista de criterios
            criterios = new ArrayList<Criterio>();
            criterio = FachadaPersistencia.getInstancia().getCriterio("fechaInicio", "<=", ServiciosTiempo.getInstancia().dateToString(this.fichaInternacion.getFechaCreacion()), "AND");
            criterios.add(criterio);
            criterio = FachadaPersistencia.getInstancia().getCriterio("fechaFin", ">=", ServiciosTiempo.getInstancia().dateToString(this.fichaInternacion.getFechaCreacion()), "AND");
            criterios.add(criterio);
            criterio = FachadaPersistencia.getInstancia().getCriterio("prestacion", "=", this.fichaInternacion.getPrestacion(), "");
            criterios.add(criterio);
            
            // se buscan los costos de la prestación que pertenezcan al período indicado y a la prestación
            List<CostoPrestacion> listaCostosPres = FachadaPersistencia.getInstancia().buscar("CostoPrestacion", criterios);
            
            // se comprueba que exista un costo para la prestación en el período
            if(!listaCostosPres.isEmpty()){
               // se toma el primer elemento de la lista de costos
               CostoPrestacion costoPres = listaCostosPres.get(0);
               
               // se guarda en el DTO de la ficha el costo de la prestación
               this.dtoFicha.setCostoPrestacion(costoPres.getMonto());
            } // fin de if de comprobación del costo de la prestación

            // se comprueba si el paciente tiene un plan de alguna obra social
            if(this.fichaInternacion.getPaciente().getPlan() != null){
               // se crea una nueva lista de criterios
               criterios = new ArrayList<Criterio>();
               criterio = FachadaPersistencia.getInstancia().getCriterio("fechaInicio", "<=", ServiciosTiempo.getInstancia().dateToString(this.fichaInternacion.getFechaCreacion()), "AND");
               criterios.add(criterio);
               criterio = FachadaPersistencia.getInstancia().getCriterio("fechaFin", ">=", ServiciosTiempo.getInstancia().dateToString(this.fichaInternacion.getFechaCreacion()), "AND");
               criterios.add(criterio);
               criterio = FachadaPersistencia.getInstancia().getCriterio("prestacion", "=", this.fichaInternacion.getPrestacion(), "AND");
               criterios.add(criterio);
               criterio = FachadaPersistencia.getInstancia().getCriterio("plan", "=", this.fichaInternacion.getPaciente().getPlan(), "");
               criterios.add(criterio);

               // se buscan los criterios que pertenezcan al período indicado y a la prestación y plan correspondiente
               List<Convenio> listaConvenios = FachadaPersistencia.getInstancia().buscar("Convenio", criterios);

               // se comprueba que exista un convenio
               if(!listaConvenios.isEmpty()){
                  // se toma el primer elemento de la lista de convenios
                  Convenio convenio = listaConvenios.get(0);
                  
                  // se guarda en la ficha el porcentaje de descuento para la prestación
                  this.dtoFicha.setDescuento(convenio.getCoseguro().getPorcentaje());
               } // fin de if de comprobación de existencia de un convenio para la prestación
            } // fin de if de comprobación del plan del paciente
            
            // se toman los detalles de la ficha de internación
            List<DetalleFicha> listaDetalles = this.fichaInternacion.getDetalleFicha();
            
            // se declara una lista de DTOs de datalles
            List<DTODetalleServicio> listaDtoDetalle = new ArrayList<DTODetalleServicio>();
            // se declara un DTO de detalle
            DTODetalleServicio dtoDetalle;

            // se recorre la lista de detalles
            for(DetalleFicha detalle : listaDetalles){
               // se crea un nuevo DTO de detalle y se le asignan los datos del detalle correspondiente
               dtoDetalle = new DTODetalleServicio();
               dtoDetalle.setNombreServicio(detalle.getServicioEspecial().getNombreServicio());
               dtoDetalle.setCantidad(detalle.getCantidad());
               
               // se crea una nueva lista de criterios
               criterios = new ArrayList<Criterio>();
               criterio = FachadaPersistencia.getInstancia().getCriterio("fechaInicio", "<=", ServiciosTiempo.getInstancia().dateToString(this.fichaInternacion.getFechaCreacion()), "AND");
               criterios.add(criterio);
               criterio = FachadaPersistencia.getInstancia().getCriterio("fechaFin", ">=", ServiciosTiempo.getInstancia().dateToString(this.fichaInternacion.getFechaCreacion()), "AND");
               criterios.add(criterio);
               criterio = FachadaPersistencia.getInstancia().getCriterio("servicioEspecial", "=", detalle.getServicioEspecial(), "");
               criterios.add(criterio);
               
               // se buscan los costos del servicio especial que pertenezcan al período indicado
               List<CostoServicio> listaCostosServ = FachadaPersistencia.getInstancia().buscar("CostoServicio", criterios);
               
               // se comprueba que se hayan encontrado costos para el servicio en el período
               if(!listaCostosServ.isEmpty()){
                  // se toma el primer elemento de la lista de costos del servicio
                  CostoServicio costoServ = listaCostosServ.get(0);

                  // se guarda en el DTO del detalle el costo del servicio especial
                  dtoDetalle.setMonto(costoServ.getMonto());
                  // se calcula el subtotal para el detalle y se guarda en éste
                  dtoDetalle.setSubtotal(costoServ.getMonto() * detalle.getCantidad());
               } // fin de if de comprobación del costo del servicio
               
               // se agrega el DTO del detalle a la lista de DTOs de detalles para el DTO de la ficha
               listaDtoDetalle.add(dtoDetalle);
            } // fin de for de creación del DTO del detalle

            // se coloca la lista de DTOs de detalles en el DTO de la ficha
            this.dtoFicha.setDtoDetalle(listaDtoDetalle);
         } // fin de if de comprobación del estado de la ficha
      } // fin de if de comprobación de existencia de la ficha
      
      // se regresa el DTO con los datos de la ficha solicitada, o null si ésta no se encontró o ya se había facturado
      return this.dtoFicha;
   } // findel método buscarFichaInternacion
   
   public DTOFacturaPaciente generarFactura(){
      // se declara un DTO de factura
      DTOFacturaPaciente dtoFactura = null;
      
      // se comprueba que la ficha efectivamente no se haya facturado
      if(!this.fichaInternacion.getEstadoFichaInternacion().getNombreEstado().equals("Facturada")){
         // se crea una nueva factura y le asigna los datos correspondientes
         FacturaPaciente factura = (FacturaPaciente) FachadaPersistencia.getInstancia().nuevaEntidad("FacturaPaciente");
         factura.setFechaEmision(new Date());
         factura.setNumFactura(this.fichaInternacion.getNroFicha());
         factura.setFichaInternacion(this.fichaInternacion);

         // se declara y crea una lista de criterios para utilizarlos en las búsquedas
         List<Criterio> criterios = new ArrayList<Criterio>();
         // declara y crea un criterio
         Criterio criterio = FachadaPersistencia.getInstancia().getCriterio("nombreEstado", "=", "Emitida", "");
         // se agrega el criterio a la lista de criterios
         criterios.add(criterio);

         // se busca el estado, para la factura, con nombre "Emitida"
         List<EstadoFacturaPaciente> listaEstadosFacturas = FachadaPersistencia.getInstancia().buscar("EstadoFacturaPaciente", criterios);

         // se comprueba que se hayan encontrado estados para la factura
         if(!listaEstadosFacturas.isEmpty()){
            // se toma el primer elemento de la lista de estados de facturas
            EstadoFacturaPaciente estadoFactura = listaEstadosFacturas.get(0);

            // se cambia el estado de la factura al nuevo estado buscado
            factura.setEstadoFacturaPaciente(estadoFactura);

            // se crea una nueva lista de criterios
            criterios = new ArrayList<Criterio>();
            criterio = FachadaPersistencia.getInstancia().getCriterio("nombreEstado", "=", "Facturada", "");
            criterios.add(criterio);

            // se buscan el estado, para la ficha, con nombre "Facturada"
            List<EstadoFichaInternacion> listaEstadosFichas = FachadaPersistencia.getInstancia().buscar("EstadoFichaInternacion", criterios);

            // se comprueba que se hayan encontrado estados para la ficha
            if(!listaEstadosFichas.isEmpty()){
               // se toma el primer elemento de la lista de estados de fichas
               EstadoFichaInternacion estadoFicha = listaEstadosFichas.get(0);

               // se cambia el estado de la ficha al nuevo estado buscado
               this.fichaInternacion.setEstadoFichaInternacion(estadoFicha);

               // se crea una nueva lista de criterios
               criterios = new ArrayList<Criterio>();
               criterio = FachadaPersistencia.getInstancia().getCriterio("nombreEstado", "=", "Disponible", "");
               criterios.add(criterio);

               // se buscan el estado, para la cama, con nombre "Disponible"
               List<EstadoCama> listaEstadosCamas = FachadaPersistencia.getInstancia().buscar("EstadoCama", criterios);

               // se comprueba que se hayan encontrado estados para la cama
               if(!listaEstadosFichas.isEmpty()){
                  // se toma el primer elemento de la lista de estados de camas
                  EstadoCama estadoCama = listaEstadosCamas.get(0);

                  // se cambia el estado de la cama al nuevo estado buscado
                  this.fichaInternacion.getCama().setEstadoCama(estadoCama);

                  // se crea un nuevo DTO de factura y se le asignan los datos correspondientes
                  dtoFactura = new DTOFacturaPaciente();
                  dtoFactura.setFecha(factura.getFechaEmision());
                  dtoFactura.setNroFicha(factura.getFichaInternacion().getNroFicha());
                  dtoFactura.setNumFactura(factura.getNumFactura());
                  dtoFactura.setNombrePaciente(this.dtoFicha.getNombrePaciente());
                  dtoFactura.setNombrePrestacion(this.dtoFicha.getNombrePrestacion());
                  dtoFactura.setCostoPrestacion(this.dtoFicha.getCostoPrestacion());
                  dtoFactura.setDescuento(this.dtoFicha.getDescuento());
                  dtoFactura.setDtoDetalle(this.dtoFicha.getDtoDetalle());

                  // se calcula el monto de la factura
                  double monto = dtoFactura.getCostoPrestacion() - dtoFactura.getCostoPrestacion() * dtoFactura.getDescuento();

                  // se suma al monto el subtotal de cada detalle de servicios
                  for(DTODetalleServicio dtoDetalle : dtoFactura.getDtoDetalle())
                     monto += dtoDetalle.getSubtotal();

                  // se guarda el monto final en la factura
                  factura.setMonto(monto);
                  // se guarda el monto final en el DTO de factura
                  dtoFactura.setMonto(monto);

                  // se guarda la factura
                  FachadaPersistencia.getInstancia().guardar("FacturaPaciente", factura);
                  // se guarda la cama
                  FachadaPersistencia.getInstancia().guardar("Cama", this.fichaInternacion.getCama());
                  // se guarda la ficha de internación
                  FachadaPersistencia.getInstancia().guardar("FichaInternacion", this.fichaInternacion);

                  // se notifica a todos los observadores acerca de la nueva factura emitida
                  SuscriptorGenerarFacturaPaciente.getInstancia().notificar(dtoFactura);
               } // fin de if de comprobación de existencia del estado de la cama
            } // fin de if de comprobación de existencia del estado de la ficha
         } // fin de if de comprobación de existencia del estado de la factura
      } // fin de if de comprobación de estado de la ficha
      
      // se devuelve el DTO de la factura
      return dtoFactura;
   } // findel método generarFactura
} // fin de la clase ExpertoGenerarFacturaPaciente