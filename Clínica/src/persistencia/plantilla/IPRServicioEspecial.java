package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.ServicioEspecialAgente;
import persistencia.proxy.ServicioEspecialImpl;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;

public class IPRServicioEspecial extends IntermPersistenciaDBR{
   public IPRServicioEspecial(){
      this.mapeoAtributos.put("oid", "oidservicio_especial");
      this.mapeoAtributos.put("codigoServicio", "codigo_servicio_especial");
      this.mapeoAtributos.put("nombreServicio", "nombre_servicio_especial");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM servicio_especial";
   } // fin del método select
   
   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM servicio_especial WHERE ";
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
      return "SELECT * FROM servicio_especial WHERE oidservicio_especial = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      ServicioEspecialAgente servicio = (ServicioEspecialAgente) objeto;
      
      return "INSERT INTO servicio_especial "
                  + "VALUES ('" + servicio.getOid() + "', "
                                + servicio.getCodigoServicio() + ", '"
                                + servicio.getNombreServicio() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      ServicioEspecialAgente servicio = (ServicioEspecialAgente) objeto;
      return "UPDATE servicio_especial SET "
                  + "codigo_servicio_especial = " + servicio.getCodigoServicio() + ", "
                  + "nombre_servicio_especial = '" + servicio.getNombreServicio() + "' "
                  + "WHERE oidservicio_especial =  '" + servicio.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      ServicioEspecialAgente servicio;
      
      try {
         while(resultado.next()){
            servicio = new ServicioEspecialAgente();
            
            servicio.setImplementacion(new ServicioEspecialImpl());
            servicio.setOid(resultado.getString("oidservicio_especial"));
            servicio.setCodigoServicio(resultado.getInt("codigo_servicio_especial"));
            servicio.setNombreServicio(resultado.getString("nombre_servicio_especial"));
            
            lista.add(servicio);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRPrestacion - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna
      
      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      ServicioEspecialAgente servicio = new ServicioEspecialAgente();
      servicio.setImplementacion(new ServicioEspecialImpl());
      
      return servicio;
   } // fin del método nuevo
} // fin de la clase IPRServicioEspecial