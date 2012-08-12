package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.ObjetoPersistente;
import persistencia.proxy.HabitacionAgente;
import persistencia.proxy.HabitacionImpl;
import persistencia.criterios.Criterio;

public class IPRHabitacion  extends IntermPersistenciaDBR {
   public IPRHabitacion(){
      this.mapeoAtributos.put("oid", "oidhabitacion");
      this.mapeoAtributos.put("nroHabitacion", "numero_habitacion");
      this.mapeoAtributos.put("sector", "oidsector");
      this.mapeoAtributos.put("tipoHabitacion", "oidtipo_habitacion");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM habitacion";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM habitacion WHERE ";
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
      return "SELECT * FROM habitacion WHERE oidhabitacion = '" + oid  + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
   HabitacionAgente habitacion = (HabitacionAgente)objeto;
   
   return "INSERT INTO habitacion "
               + "VALUES ('" + habitacion.getOid() + "', "
                             + habitacion.getNroHabitacion() + ", '"
                             + habitacion.getOidSector() + "', '"
                             + habitacion.getOidTipoHabitacion() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      HabitacionAgente habitacion = (HabitacionAgente)objeto;
      
      return "UPDATE habitacion SET "
               + "numero_habitacion = " + habitacion.getNroHabitacion() + ", "
               + "oidsector = '" + habitacion.getOidSector() + "', "
               + "oidtipo_habitacion = '" + habitacion.getOidTipoHabitacion() + "' "
               + "WHERE oidhabitacion = '" + habitacion.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet rs) {
      List<ObjetoPersistente> lista =  new ArrayList<ObjetoPersistente>();
      HabitacionAgente habitacion;
      
      try {
         while (rs.next()){
            habitacion = new HabitacionAgente();

            habitacion.setImplementacion(new HabitacionImpl());
            habitacion.setOid(rs.getString("oidhabitacion"));
            habitacion.setNroHabitacion(rs.getInt("numero_habitacion"));
            habitacion.setOidSector(rs.getString("oidsector"));
            habitacion.setOidTipoHabitacion(rs.getString("oidtipo_habitacion"));

            lista.add(habitacion);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.out.println("IPRHabitacionn - convertirAObjeto - SQLException: "+ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna
      
      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      HabitacionAgente habitacion = new HabitacionAgente();
      habitacion.setImplementacion(new HabitacionImpl());
      
      return (ObjetoPersistente) habitacion;
   } // fin del método nuevo
} // fin de la clase IPRHabitacion