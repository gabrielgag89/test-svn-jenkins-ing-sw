package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.CostoServicioAgente;
import persistencia.proxy.CostoServicioImpl;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;
import util.ServiciosTiempo;

public class IPRCostoServicio extends IntermPersistenciaDBR {
   public IPRCostoServicio(){
      this.mapeoAtributos.put("oid", "oidcosto_servicio");
      this.mapeoAtributos.put("fechaInicio", "fecha_inicio");
      this.mapeoAtributos.put("fechaFin", "fecha_fin");
      this.mapeoAtributos.put("monto", "monto");
      this.mapeoAtributos.put("servicioEspecial", "oidservicio_especial");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM costo_servicio";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM costo_servicio WHERE ";
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
      return "SELECT * FROM costo_servicio WHERE oidcosto_servicio = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      CostoServicioAgente costoServicio = (CostoServicioAgente) objeto;

      return "INSERT INTO costo_servicio "
               + "VALUES ('" + costoServicio.getOid() + "', '"
                             + ServiciosTiempo.getInstancia().dateToString(costoServicio.getFechaInicio()) + "', '"
                             + ServiciosTiempo.getInstancia().dateToString(costoServicio.getFechaFin()) + "', "
                             + costoServicio.getMonto()+ ", '"
                             + costoServicio.getOidServicioEspecial() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      CostoServicioAgente costoServicio = (CostoServicioAgente) objeto;
      
      return "UPDATE costo_servicio SET "
               + "fecha_inicio = '"+ ServiciosTiempo.getInstancia().dateToString(costoServicio.getFechaInicio()) + "', "
               + "fecha_fin = '" + ServiciosTiempo.getInstancia().dateToString(costoServicio.getFechaFin()) + "', "
               + "monto = " + costoServicio.getMonto() + ", "
               + "oidservicio_especial = '" + costoServicio.getOidServicioEspecial() + "' "
               + "WHERE oidcosto_servicio =  '" + costoServicio.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      CostoServicioAgente costoServicio;
      
      try {
         while(resultado.next()){
            costoServicio = new CostoServicioAgente();
            
            costoServicio.setImplementacion(new CostoServicioImpl());
            costoServicio.setOid(resultado.getString("oidcosto_servicio"));
            costoServicio.setFechaInicio(resultado.getDate("fecha_inicio"));
            costoServicio.setFechaFin(resultado.getDate("fecha_fin"));
            costoServicio.setMonto(resultado.getFloat("monto"));
            costoServicio.setOidServicioEspecial(resultado.getString("oidservicio_especial"));
            
            lista.add(costoServicio);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRCostoServicio - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      CostoServicioAgente costoServicio = new CostoServicioAgente();
      costoServicio.setImplementacion(new CostoServicioImpl());
      
      return costoServicio;
   } // fin del método nuevo
}