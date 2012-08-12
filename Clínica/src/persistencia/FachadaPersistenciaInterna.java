package persistencia;

import java.util.List;
import java.sql.SQLException;
import persistencia.plantilla.FabricaDeIntermediarios;
import persistencia.criterios.FabricaDeCriterios;
import persistencia.criterios.Criterio;

public class FachadaPersistenciaInterna {
   private static FachadaPersistenciaInterna instancia;
   
   private FachadaPersistenciaInterna(){}
   
   public static FachadaPersistenciaInterna getInstancia(){
      if (instancia == null)
         instancia = new FachadaPersistenciaInterna();
      
      return instancia;
   } // fin del método getInstancia
   
   public void iniciarTransaccion(){
      Conector.getInstancia().iniciarTransaccion();
   } // fin del método iniciarTransaccion
   
   public void finalizarTransaccion(boolean resultado){
      if(resultado)
         Conector.getInstancia().confirmarTransaccion();
      else
         Conector.getInstancia().deshacerTransaccion();
   } // fin del método finalizarTransaccion
   
   public Object nuevaEntidad(String entidad){
      return FabricaDeIntermediarios.getInstancia().getIntermediario(entidad).nuevaEntidad();
   } // fin del método nuevaEntidad
   
   public List buscar(String entidad){
       return FabricaDeIntermediarios.getInstancia().getIntermediario(entidad).buscar();
   }
   
   public List buscar(String objeto, List<Criterio> criterios) {
      return FabricaDeIntermediarios.getInstancia().getIntermediario(objeto).buscar(criterios);
   } // fin del método getColeccion
   
   public Object buscar(String entidad, String oid){
      return FabricaDeIntermediarios.getInstancia().getIntermediario(entidad).buscar(oid);
   } // fin del método obtenerEntidad
   
   public void guardar(String entidad, Object objeto){
      FabricaDeIntermediarios.getInstancia().getIntermediario(entidad).guardar(objeto);
   } // fin del método persistirEntidad
   
   public Criterio getCriterio(String atributo, String operador, Object valor, String opLogico){
      return FabricaDeCriterios.getInstancia().getCriterio(atributo, operador, valor, opLogico);
   } // fin del método getCriterio
} // fin de la clase FachadaPersistenciaInterna