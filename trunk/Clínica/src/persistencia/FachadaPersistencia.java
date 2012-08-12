package persistencia;

import java.util.List;
import persistencia.criterios.Criterio;

public class FachadaPersistencia {
   private static FachadaPersistencia instancia;
   
   private FachadaPersistencia(){}
   
   public static FachadaPersistencia getInstancia(){
      if (instancia == null)
         instancia = new FachadaPersistencia();
      
      return instancia;
   } // fin del método getInstancia
   
   public Object nuevaEntidad(String entidad){
      return FachadaPersistenciaInterna.getInstancia().nuevaEntidad(entidad);
   } // fin del método nuevaEntidad
   
   public List buscar(String entidad){
       return FachadaPersistenciaInterna.getInstancia().buscar(entidad);
   } // fin del método 
   
   public List buscar(String entidad, List<Criterio> criterios) {
      return FachadaPersistenciaInterna.getInstancia().buscar(entidad, criterios);
   } // fin del método buscar
   
   public void guardar(String entidad , Object objeto){
      FachadaPersistenciaInterna.getInstancia().guardar(entidad, objeto);
   } // fin del método guardar
   
   public Criterio getCriterio(String atributo, String operador, Object valor, String opLogico){
      return  FachadaPersistenciaInterna.getInstancia().getCriterio(atributo, operador, valor, opLogico);
   } // fin del método getCriterio
} // fin de la clase FachadaPersistencia