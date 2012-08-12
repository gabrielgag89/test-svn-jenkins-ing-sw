package generar_factura_paciente;

import java.util.List;
import persistencia.FachadaPersistenciaInterna;
import dtos.DTOFichaInternacion;
import dtos.DTOFacturaPaciente;

public class DecoradorExpertoGenerarFacturaPaciente extends ExpertoGenerarFacturaPaciente {
   @Override
   public List<DTOFichaInternacion> buscarFichasPendientes(){
      List<DTOFichaInternacion> listaDtoFichas = null;
      
      try {
         // se inicia la transacción
         FachadaPersistenciaInterna.getInstancia().iniciarTransaccion();
         
         // se buscan las fichas de internación que aún no hayan sido facturadas
         listaDtoFichas = super.buscarFichasPendientes();
         
         // se confirma la transacción
         FachadaPersistenciaInterna.getInstancia().finalizarTransaccion(true);
      } // fin de try de búsqueda de las fichas no facturadas
      catch (Exception ex) {
         System.err.println("Exception en buscarFichasCreadas: " + ex.getStackTrace());
      } // fin de catch de Exception
      
      return listaDtoFichas;
   } // fin del método buscarFichasPendientes
   
   @Override
   public DTOFichaInternacion buscarFichaInternacion(int numFicha){
      DTOFichaInternacion dtoFicha = null;
      
      try {
         // se inicia la transacción
         FachadaPersistenciaInterna.getInstancia().iniciarTransaccion();
         
         // se busca la ficha de internación correspondiente al número recibido y se devuelve el DTO con los datos de la ficha
         dtoFicha = super.buscarFichaInternacion(numFicha);
      } // fin de try de búsqueda de la ficha
      catch (Exception ex) {
         ex.printStackTrace();
      } // fin de catch de Exception
      
      return dtoFicha;
   } // fin del método buscarFichaInternacion

   @Override
   public DTOFacturaPaciente generarFactura() {
      DTOFacturaPaciente dtoFactura = null;
      
      try {
         // se genera la factura sobre la ficha de internación correspondiente y se devuelve el DTO con los datos de ésta
         dtoFactura = super.generarFactura();
         
         // se confirma la transacción
         FachadaPersistenciaInterna.getInstancia().finalizarTransaccion(true);
      } // fin de try de generación de factura
      catch (Exception ex) {
         System.err.println("Exception en generarFactura: " + ex.getStackTrace());
         
         // se deshace la transacción
         FachadaPersistenciaInterna.getInstancia().finalizarTransaccion(false);
      } // fin de catch de Exception
      
      return dtoFactura;
   } // fin del método generarFactura
} // fin de la clase DecoradorExpertoGenerarFacturaPaciente