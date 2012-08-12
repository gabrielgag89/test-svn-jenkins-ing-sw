
package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.TipoPrestacionAgente;
import persistencia.proxy.TipoPrestacionImpl;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;

public class IPRTipoPrestacion extends IntermPersistenciaDBR {
   public IPRTipoPrestacion(){
      this.mapeoAtributos.put("oid", "oidtipo_prestacion");
      this.mapeoAtributos.put("codigoTipoPrestacion", "codigo_tipo_prestacion");
      this.mapeoAtributos.put("nombreTipoPrestacion", "nombre_tipo_prestacion");
      this.mapeoAtributos.put("tipoHabitacion", "oidtipo_habitacion");
   } // fin del constructor
   
   @Override
   public String select() {
   return "SELECT * FROM tipo_prestacion";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM tipo_prestacion WHERE ";
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
      return "SELECT * FROM tipo_prestacion WHERE oidtipo_prestacion = '" + oid  + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      TipoPrestacionAgente tipoP = (TipoPrestacionAgente)objeto;

      return "INSERT INTO tipo_prestacion "
                  + "VALUES ('" + tipoP.getOid() + "', "
                                + tipoP.getCodigoTipoPrestacion() + ", '"                            
                                + tipoP.getNombreTipoPrestacion() + "', '"
                                + tipoP.getOidTipoHabitacion()+ "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      TipoPrestacionAgente tipoP = (TipoPrestacionAgente)objeto;

      return "UPDATE tipo_prestacion SET "
                  + "codigo_tipo_prestacion = " + tipoP.getCodigoTipoPrestacion() + ", "                            
                  + "nombre_tipo_prestacion = '" + tipoP.getNombreTipoPrestacion() + "', "
                  + "oidtipo_habitacion = '"+ tipoP.getOidTipoHabitacion()+ "' "
                  + "WHERE oidtipo_prestacion = '" + tipoP.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      TipoPrestacionAgente tipoP;

      try {
         while(resultado.next()){
            tipoP = new TipoPrestacionAgente();

            tipoP.setImplementacion(new TipoPrestacionImpl());
            tipoP.setOid(resultado.getString("oidtipo_prestacion"));
            tipoP.setCodigoTipoPrestacion(resultado.getInt("codigo_tipo_prestacion"));
            tipoP.setNombreTipoPrestacion(resultado.getString("nombre_tipo_prestacion"));
            tipoP.setOidTipoHabitacion(resultado.getString("oidtipo_habitacion"));

            lista.add(tipoP);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRTipoPrestacion - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      TipoPrestacionAgente tipoP = new TipoPrestacionAgente();
      tipoP.setImplementacion(new TipoPrestacionImpl());
      
      return tipoP;
   } // fin del método nuevo
} // fin de la clase IPRTipoPrestacion