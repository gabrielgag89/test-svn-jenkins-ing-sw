package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.ObjetoPersistente;
import persistencia.proxy.PlanImpl;
import persistencia.criterios.Criterio;
import persistencia.proxy.PlanAgente;

public class IPRPlan extends IntermPersistenciaDBR {
   public IPRPlan(){
      this.mapeoAtributos.put("oid", "oidplan");
      this.mapeoAtributos.put("codigoPlan", "codigo_plan");
      this.mapeoAtributos.put("descripcion", "descripcion");
      this.mapeoAtributos.put("obraSocial", "oidobra_social");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM plan";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM plan WHERE ";
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
      return "SELECT * FROM plan WHERE oidplan = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      PlanAgente plan = (PlanAgente) objeto;
      
      return "INSERT INTO plan "
               + "VALUES ('" + plan.getOid() + "', "
                             + plan.getCodigoPlan() + ", '"
                             + plan.getDescripcion() + "', '"
                             + plan.getOidObraSocial() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      PlanAgente plan = (PlanAgente) objeto;
      
      return "UPDATE plan SET "
               + "codigo_plan = " + plan.getCodigoPlan() + ", "
               + "descripcion = '" + plan.getDescripcion() + "', "
               + "oidobra_social = '" + plan.getOidObraSocial() + "' "
               + "WHERE oidplan = '" + plan.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      PlanAgente plan;

      try {
         while(resultado.next()){
            plan = new PlanAgente();

            plan.setImplementacion(new PlanImpl());
            plan.setOid(resultado.getString("oidplan"));
            plan.setCodigoPlan(resultado.getInt("codigo_plan"));
            plan.setDescripcion(resultado.getString("descripcion"));
            plan.setOidObraSocial(resultado.getString("oidobra_social"));

            lista.add(plan);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRPlan - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      PlanAgente plan = new PlanAgente();
      plan.setImplementacion(new PlanImpl());

      return plan;
   } // fin del método nuevo
} // fin de la clase IPRPlan