package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.ObjetoPersistente;
import persistencia.proxy.EstadoCamaAgente;
import persistencia.proxy.EstadoCamaImpl;
import persistencia.criterios.Criterio;

public class IPREstadoCama extends IntermPersistenciaDBR {
   public IPREstadoCama(){
      this.mapeoAtributos.put("oid", "oidestado_cama");
      this.mapeoAtributos.put("nombreEstado", "nombre_estado_cama");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM estado_cama";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM estado_cama WHERE ";
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
      return "SELECT * FROM estado_cama WHERE oidestado_cama = '" + oid +"'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      EstadoCamaAgente estadoC = (EstadoCamaAgente)objeto;

      return "INSERT INTO estado_cama "
                  + "VALUES ('" + estadoC.getOid() + "','"
                                + estadoC.getNombreEstado()+ "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      EstadoCamaAgente estadoC = (EstadoCamaAgente)objeto;
      
      return "UPDATE estado_cama SET "
               + "nombre_estado_cama = '" + estadoC.getNombreEstado() + "' "
               + "WHERE oidestado_cama = '" + estadoC.getOid() + "'";                            
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      EstadoCamaAgente estado;

      try {
         while(resultado.next()){
            estado = new EstadoCamaAgente();

            estado.setImplementacion(new EstadoCamaImpl());
            estado.setOid(resultado.getString("oidestado_cama"));
            estado.setNombreEstado(resultado.getString("nombre_estado_cama"));

            lista.add(estado);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPREstadoCama - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      EstadoCamaAgente estado = new EstadoCamaAgente();
      estado.setImplementacion(new EstadoCamaImpl());
      
      return estado;
   } // fin del método nuevo
} // fin de la clase IPREstadoCama