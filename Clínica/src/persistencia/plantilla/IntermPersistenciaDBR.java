package persistencia.plantilla;

import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;
import persistencia.Conector;

public abstract class IntermPersistenciaDBR extends IntermediarioPersistencia{
   protected HashMap<String, String> mapeoAtributos = new HashMap<String, String>();
   
   @Override
   public List<ObjetoPersistente> materializar(){
      String sql = select();
      ResultSet resultado = ejecutarSQL(sql);

      return convertirAObjeto(resultado);
   } // fin del método materializar
   
   @Override
   public List<ObjetoPersistente> materializar(List<Criterio> criterios){
      String sql = select(criterios);
      ResultSet resultado = ejecutarSQL(sql);

      return convertirAObjeto(resultado);
   } // fin del método materializar
   
   @Override
   public ObjetoPersistente materializar(String oid){
      String sql = select(oid);
      ResultSet resultado = ejecutarSQL(sql);
      List<ObjetoPersistente> buscado = convertirAObjeto(resultado);

      return buscado.get(0);
   } // fin del método materializar
   
   @Override
   public void desmaterializar(ObjetoPersistente objeto){
      String sql;
      
      if(objeto.getNuevo())
         sql = insertar(objeto);
      else
         sql = actualizar(objeto);
         
      ejecutarSQLSave(sql);
   } // fin del método desmaterializar
   
   @Override
   public ObjetoPersistente obtenerNuevaEntidad(){
      ObjetoPersistente objPers = nuevo();
      objPers.setOid(UUID.randomUUID().toString());
      
      return objPers;
   } // fin del método obtenerNuevaEntidad
   
   protected String getNombreColumna(String nombreAtributo){
      return this.mapeoAtributos.get(nombreAtributo);
   } // fin del método getNombreColumna

   private void ejecutarSQLSave(String sql){
      System.out.println(sql);
      
      try{
         PreparedStatement consulta = Conector.getInstancia().getConexion().prepareStatement(sql);

         consulta.execute();
      }
      catch (SQLException ex) {
         System.err.println("IntermPersistenciaDBR-ejecutarSQL(String sql) - SQLException: " + ex.getMessage());
      }
   } // fin del método ejecutarSQLSave

   private ResultSet ejecutarSQL(String sql){
      System.out.println(sql);
      
      try{
         PreparedStatement consulta = Conector.getInstancia().getConexion().prepareStatement(sql);

         return consulta.executeQuery(sql);
      }
      catch (SQLException ex) {
         System.err.println("IntermPersistenciaDBR-ejecutarSQL(String sql) - SQLException: " + ex.getMessage());
         return null;
      }
   } // fin del método ejecutarSQL
   
   public abstract String select(); // método a implementar
   
   public abstract String select(List<Criterio> criterios); // método a implementar
   
   public abstract String select(String oid); // método a implementar
   
   public abstract String insertar(Object objeto); // método a implementar
   
   public abstract String actualizar(Object objeto); // método a implementar
   
   public abstract List<ObjetoPersistente> convertirAObjeto(ResultSet resultado); // método a implementar
   
   public abstract ObjetoPersistente nuevo(); // método a implementar
} // fin de la clase IntermPersistenciaDBR