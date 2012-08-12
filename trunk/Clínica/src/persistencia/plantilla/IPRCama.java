package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;
import persistencia.proxy.CamaAgente;
import persistencia.proxy.CamaImpl;

public class IPRCama extends IntermPersistenciaDBR {
   public IPRCama(){
      this.mapeoAtributos.put("oid", "oidcama");
      this.mapeoAtributos.put("numCama", "numero_cama");
      this.mapeoAtributos.put("habitacion", "oidhabitacion");
      this.mapeoAtributos.put("estadoCama", "oidestado_cama");
   } // fin del constructor

   @Override
   public String select() {
      return "SELECT * FROM cama";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM cama WHERE ";
      int cont = 0;
      
      for(Criterio criterio : criterios){
         sql += " (" + this.getNombreColumna(criterio.getAtributo()) + " "
                 + criterio.getOperador() + " '"
                 + criterio.getValor().toString() + "') "
                 + criterio.getOpLogico();
         
         cont++;
      }
      
      return sql;
   } // fin del método select

   @Override
   public String select(String oid) {
      return "SELECT * FROM cama WHERE oidcama = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      CamaAgente cama = (CamaAgente) objeto;
      
      return "INSERT INTO cama "
               + "VALUES ('" + cama.getOid() + "', "
                             + cama.getNumCama() + ", '"
                             + cama.getOidHabitacion()+ "', '"
                             + cama.getOidEstadoCama() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      CamaAgente cama = (CamaAgente) objeto;
      
      return "UPDATE cama SET "
               + "numero_cama = " + cama.getNumCama() + ", "
               + "oidhabitacion = '" + cama.getOidHabitacion() + "', "
               + "oidestado_cama = '" + cama.getOidEstadoCama() + "' "
               + "WHERE oidcama = '" + cama.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      CamaAgente cama;
      
      try {
         while(resultado.next()){
            cama = new CamaAgente();
            
            cama.setImplementacion(new CamaImpl());
            cama.setOid(resultado.getString("oidcama"));
            cama.setNumCama(resultado.getInt("numero_cama"));
            cama.setOidHabitacion(resultado.getString("oidhabitacion"));
            cama.setOidEstadoCama(resultado.getString("oidestado_cama"));
            
            lista.add(cama);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRCama - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      CamaAgente cama = new CamaAgente();
      cama.setImplementacion(new CamaImpl());
      
      return cama;
   } // fin del método nuevo
} // fin de la clase IPRCama