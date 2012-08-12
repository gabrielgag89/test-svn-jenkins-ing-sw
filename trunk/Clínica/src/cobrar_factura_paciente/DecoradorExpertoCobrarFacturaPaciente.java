package cobrar_factura_paciente;

import java.util.List;
import persistencia.FachadaPersistenciaInterna;
import dtos.DTOFacturaPaciente;
import dtos.DTORecibo;

public class DecoradorExpertoCobrarFacturaPaciente extends ExpertoCobrarFacturaPaciente{
   @Override
   public List<DTOFacturaPaciente> buscarFacturasPendientes() {
      List<DTOFacturaPaciente> listaDtoFacturas = null;
      
      try {
         // se inicia la transacción
         FachadaPersistenciaInterna.getInstancia().iniciarTransaccion();

         // se buscan las facturas pendientes de cobro
         listaDtoFacturas = super.buscarFacturasPendientes();
      } // fin de try de búsqueda de facturas pendientes
      catch (Exception ex) {
         System.err.println("Exception en buscarFacturasPendientes: " + ex.getMessage());
      } // fin de catch de Exception
      
      return listaDtoFacturas;
   } // fin del método buscarFacturasPendientes
   
   @Override
   public DTORecibo cobrarFactura(int numFactura){
      DTORecibo dtoRecibo = null;
      
      try {
         // se genera el recibo correspondiente a la factura indicada
         dtoRecibo =super.cobrarFactura(numFactura);
         
         // se confirma la transacción
         FachadaPersistenciaInterna.getInstancia().finalizarTransaccion(true);
      } // fin de try de fin de transacción
      catch (Exception ex) {
         System.err.println("SQLException en cobrarFactura: " + ex.getMessage());
         
         // se deshace la transacción
         FachadaPersistenciaInterna.getInstancia().finalizarTransaccion(false);
      } // fin de catch de SQLException
      
      return dtoRecibo;
   } // fin del método cobrarFactura
} // fin de la clase DecoradorExpertoCobrarFacturaPaciente