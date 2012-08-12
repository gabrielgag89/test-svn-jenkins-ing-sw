package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.criterios.Criterio;
import persistencia.proxy.ObjetoPersistente;
import persistencia.proxy.ConvenioAgente;
import persistencia.proxy.ConvenioImpl;
import util.ServiciosTiempo;

public class IPRConvenio extends IntermPersistenciaDBR{
   public IPRConvenio(){
      this.mapeoAtributos.put("oid", "oidconvenio");
      this.mapeoAtributos.put("fechaInicio", "fecha_inicio");
      this.mapeoAtributos.put("fechaFin", "fecha_fin");
      this.mapeoAtributos.put("plan", "oidplan");
      this.mapeoAtributos.put("prestacion", "oidprestacion");
      this.mapeoAtributos.put("coseguro", "oidcoseguro");
   } // fin del constructor

   @Override
   public String select() {
      return "SELECT * FROM convenio";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM convenio WHERE ";
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
      return "SELECT * FROM convenio WHERE oidconvenio = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      ConvenioAgente conv = (ConvenioAgente) objeto;
      
      return "INSERT INTO convenio "
                  + "VALUES ('" + conv.getOid() + "', "
                                + ServiciosTiempo.getInstancia().dateToString(conv.getFechaInicio()) + ", "
                                + ServiciosTiempo.getInstancia().dateToString(conv.getFechaFin()) + ", '"
                                + conv.getOidPlan() + "', '"
                                + conv.getOidPrestacion() + "', '"
                                + conv.getOidCoseguro() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      ConvenioAgente conv = (ConvenioAgente) objeto;
      
      return "UPDATE convenio SET "
                  + "fecha_inicio = " + ServiciosTiempo.getInstancia().dateToString(conv.getFechaInicio()) + ", "
                  + "fecha_fin = " + ServiciosTiempo.getInstancia().dateToString(conv.getFechaFin()) + ", "
                  + "oidplan = '" + conv.getOidPlan() + "', "
                  + "oidprestacion = '" + conv.getOidPrestacion() + "', "
                  + "oidcoseguro = '" + conv.getOidCoseguro() + "' "
                  + "WHERE oidconvenio =  '" + conv.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      ConvenioAgente conv;
      
      try {
         while(resultado.next()){
            conv = new ConvenioAgente();
            
            conv.setImplementacion(new ConvenioImpl());
            conv.setOid(resultado.getString("oidconvenio"));
            conv.setFechaInicio(resultado.getDate("fecha_inicio"));
            conv.setFechaFin(resultado.getDate("fecha_fin"));
            conv.setOidPlan(resultado.getString("oidplan"));
            conv.setOidPrestacion(resultado.getString("oidprestacion"));
            conv.setOidCoseguro(resultado.getString("oidcoseguro"));
            
            lista.add(conv);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRconvenio - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna
      
      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      ConvenioAgente conv = new ConvenioAgente();
      conv.setImplementacion(new ConvenioImpl());
      
      return conv;
   } // fin del método nuevo
} // fin de la clase IPRConvenio