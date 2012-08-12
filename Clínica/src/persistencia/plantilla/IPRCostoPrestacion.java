package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.CostoPrestacionAgente;
import persistencia.proxy.CostoPrestacionImpl;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;
import util.ServiciosTiempo;

public class IPRCostoPrestacion extends IntermPersistenciaDBR {
   public IPRCostoPrestacion(){
      this.mapeoAtributos.put("oid", "oidcosto_prestacion");
      this.mapeoAtributos.put("fechaInicio", "fecha_inicio");
      this.mapeoAtributos.put("fechaFin", "fecha_fin");
      this.mapeoAtributos.put("monto", "monto");
      this.mapeoAtributos.put("prestacion", "oidprestacion");
   } // fin del constructor

   @Override
   public String select() {
      return "SELECT * FROM costo_prestacion";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM costo_prestacion WHERE ";
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
      return "SELECT * FROM costo_prestacion WHERE oidcosto_prestacion ='" + oid +"')";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
   CostoPrestacionAgente costo = (CostoPrestacionAgente)objeto;

   return "INSERT INTO costo_prestacion "
               + "VALUES ('" + costo.getOid() + "', '"
                             + ServiciosTiempo.getInstancia().dateToString(costo.getFechaInicio()) + "', '"                            
                             + ServiciosTiempo.getInstancia().dateToString(costo.getFechaFin()) + "', "
                             + costo.getMonto() + ", '"
                             + costo.getOidPrestacion()+ "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      CostoPrestacionAgente costo = (CostoPrestacionAgente)objeto;

      return "UPDATE costo_prestacion SET "
               + "fecha_inicio = '" + ServiciosTiempo.getInstancia().dateToString(costo.getFechaInicio()) + "', "                            
               + "fecha_fin  = '" + ServiciosTiempo.getInstancia().dateToString(costo.getFechaFin()) + "', "
               + "monto = " + costo.getMonto() + ", "
               + "oidprestacion = '"+ costo.getOidPrestacion()+ "' "
               + "WHERE oidcosto_prestacion = '" + costo.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      CostoPrestacionAgente costo;

      try {
         while(resultado.next()){
            costo = new CostoPrestacionAgente();

            costo.setImplementacion(new CostoPrestacionImpl());
            costo.setOid(resultado.getString("oidcosto_prestacion"));
            costo.setFechaInicio(resultado.getDate("fecha_inicio"));
            costo.setFechaFin(resultado.getDate("fecha_fin"));
            costo.setMonto(resultado.getFloat("monto"));
            costo.setOidPrestacion(resultado.getString("oidprestacion"));

            lista.add(costo);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRCostoPrestacion - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      CostoPrestacionAgente costo = new CostoPrestacionAgente();
      costo.setImplementacion(new CostoPrestacionImpl());

      return costo;
   } // fin del método 
} // fin de la clase IPRCostoPrestacion