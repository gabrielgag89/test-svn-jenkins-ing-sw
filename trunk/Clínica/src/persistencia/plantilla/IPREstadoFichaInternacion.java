package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.EstadoFichaInternacionAgente;
import persistencia.proxy.EstadoFichaInternacionImpl;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;

public class IPREstadoFichaInternacion extends IntermPersistenciaDBR{
   public IPREstadoFichaInternacion(){
      this.mapeoAtributos.put("oid", "oidestado_ficha_internacion");
      this.mapeoAtributos.put("nombreEstado", "nombre_estado_ficha_internacion");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM estado_ficha_internacion";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM estado_ficha_internacion WHERE ";
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
      return "SELECT * FROM estado_ficha_internacion WHERE oidestado_ficha_internacion = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      EstadoFichaInternacionAgente estado = (EstadoFichaInternacionAgente) objeto;

      return "INSERT INTO estado_ficha "
                  + "VALUES ('" + estado.getOid() + "', '"
                                + estado.getNombreEstado() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      EstadoFichaInternacionAgente estado = (EstadoFichaInternacionAgente)objeto;

      return "UPDATE estado_ficha SET "
               + "nombre_estado_ficha_internacion = '" + estado.getNombreEstado() + "' " 
               + "WHERE oidestado_ficha_internacion = '" + estado.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      EstadoFichaInternacionAgente estado;

      try {
         while(resultado.next()){
            estado = new EstadoFichaInternacionAgente();

            estado.setImplementacion(new EstadoFichaInternacionImpl());
            estado.setOid(resultado.getString("oidestado_ficha_internacion"));
            estado.setNombreEstado(resultado.getString("nombre_estado_ficha_internacion"));

            lista.add(estado);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPREstadoFichaInternacion - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      EstadoFichaInternacionAgente estado = new EstadoFichaInternacionAgente();
      estado.setImplementacion(new EstadoFichaInternacionImpl());

      return estado;
    } // fin del método nuevo
} // fin de la clase IPREstadoFichaInternacion