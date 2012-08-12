package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.DetalleFichaAgente;
import persistencia.proxy.ObjetoPersistente;
import persistencia.proxy.DetalleFichaImpl;
import persistencia.criterios.Criterio;

public class IPRDetalleFicha extends IntermPersistenciaDBR {
   public IPRDetalleFicha(){
      this.mapeoAtributos.put("oid", "oiddetalle_ficha");
      this.mapeoAtributos.put("cantidad", "cantidad");
      this.mapeoAtributos.put("fichaInternacion", "oidficha_internacion");
      this.mapeoAtributos.put("servicioEspecial", "oidservicio_especial");
   } // fin del constructor

   @Override
   public String select() {
      return "SELECT * FROM detalle_ficha";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM detalle_ficha WHERE ";
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
      return "SELECT * FROM detalle_ficha WHERE oidcama = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      DetalleFichaAgente detalleficha = (DetalleFichaAgente) objeto;

      return "INSERT INTO detalle_ficha "
               + "VALUES ('" + detalleficha.getOid() + "', "
                             + detalleficha.getCantidad() + ", '"
                             + detalleficha.getOidFichaInternacion() + "', '"
                             + detalleficha.getOidServicioEspecial() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      DetalleFichaAgente detalleficha = (DetalleFichaAgente) objeto;

      return "UPDATE detalle_ficha SET "
               + "cantidad = " + detalleficha.getCantidad() + ", "
               + "oidficha_internacion = '" + detalleficha.getOidFichaInternacion() + "', "
               + "oidservicio_especial = '" + detalleficha.getOidServicioEspecial() + "' "
               + "WHERE oiddetalle_ficha = '" + detalleficha.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      DetalleFichaAgente detalleFicha;
      
      try {
         while(resultado.next()){
            detalleFicha = new DetalleFichaAgente();
            
            detalleFicha.setImplementacion(new DetalleFichaImpl());
            detalleFicha.setOid(resultado.getString("oiddetalle_ficha"));
            detalleFicha.setCantidad(resultado.getInt("cantidad"));
            detalleFicha.setOidFichaInterncaion(resultado.getString("oidficha_internacion"));
            detalleFicha.setOidServicioEspecial(resultado.getString("oidservicio_especial"));
             
            lista.add(detalleFicha);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRDetalleFicha - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      DetalleFichaAgente detalleFicha = new DetalleFichaAgente();
      detalleFicha.setImplementacion(new DetalleFichaImpl());
      
      return detalleFicha;
   } // fin del método nuevo
} // fin de la clase 