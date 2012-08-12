package persistencia.plantilla;
  
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.TipoHabitacionAgente;
import persistencia.proxy.TipoHabitacionImpl;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;

public class IPRTipoHabitacion extends IntermPersistenciaDBR{
   public IPRTipoHabitacion(){
      this.mapeoAtributos.put("oid", "oidtipo_habitacion");
      this.mapeoAtributos.put("codigoTipoHab", "codigo_tipo_habitacion");
      this.mapeoAtributos.put("nombreTipo", "nombre_tipo_habitacion");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM tipo_habitacion";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM tipo_habitacion WHERE ";
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
      return "SELECT * FROM tipo_habitacion WHERE oidtipo_habitacion = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      TipoHabitacionAgente tipoHab = (TipoHabitacionAgente) objeto;
      
      return "INSERT INTO tipo_habitacion "
                  + "VALUES ('" + tipoHab.getOid() + "', "
                                + tipoHab.getCodigoTipoHabitacion() + ", '"
                                + tipoHab.getNombreTipo() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      TipoHabitacionAgente tipoHab = (TipoHabitacionAgente) objeto;

      return "UPDATE tipohabitacion SET "
                  + "codigo_tipo_habitacion = " + tipoHab.getCodigoTipoHabitacion() + ", "
                  + "nombre_tipo_habitacion = '" + tipoHab.getNombreTipo() + "' "
                  + "WHERE oidtipo_habitacion =  '" + tipoHab.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      TipoHabitacionAgente tipoHab;

      try {
         while(resultado.next()){
            tipoHab = new TipoHabitacionAgente();

            tipoHab.setImplementacion(new TipoHabitacionImpl());
            tipoHab.setOid(resultado.getString("oidtipo_habitacion"));
            tipoHab.setCodigoTipoHabitacion(resultado.getInt("codigo_tipo_habitacion"));
            tipoHab.setNombreTipo(resultado.getString("nombre_tipo_habitacion"));
            
            lista.add(tipoHab);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRTipoHabitacion - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      TipoHabitacionAgente tipoHab = new TipoHabitacionAgente();
      tipoHab.setImplementacion(new TipoHabitacionImpl());

      return tipoHab;
   } // fin del método nuevo
} // fin de la clase IPRTipoHabitacion