package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.ObjetoPersistente;
import persistencia.proxy.PacienteAgente;
import persistencia.proxy.PacienteImpl;
import persistencia.criterios.Criterio;

public class IPRPaciente extends IntermPersistenciaDBR {
   public IPRPaciente(){
      this.mapeoAtributos.put("oid", "oidpaciente");
      this.mapeoAtributos.put("numeroPaciente", "numero_paciente");
      this.mapeoAtributos.put("nombrePaciente", "nombre_paciente");
      this.mapeoAtributos.put("dni", "dni");
      this.mapeoAtributos.put("domicilio", "domicilio");
      this.mapeoAtributos.put("telefono", "telefono");
      this.mapeoAtributos.put("plan", "oidplan");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM paciente";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM paciente WHERE ";
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
      return "SELECT * FROM paciente WHERE oidpaciente = '" + oid  + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
   PacienteAgente paciente = (PacienteAgente)objeto;

   return "INSERT INTO paciente "
            + "VALUES ('" + paciente.getOid() + "', "
                          + paciente.getNumPaciente() + ", '"                            
                          + paciente.getNombre() + "', "
                          + paciente.getDni() + ", '"
                          + paciente.getDomicilio() + "', '"
                          + paciente.getTelefono() +"', '"
                          + paciente.getOidPlan()+ "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      PacienteAgente paciente = (PacienteAgente)objeto;
      
      return "UPDATE paciente SET "
               + "numero_paciente = " + paciente.getNumPaciente() + ", "                            
               + "nombre_paciente = '" + paciente.getNombre() + "', "
               + "dni = " + paciente.getDni() + ", "
               + "domicilio = '"+ paciente.getDomicilio() + "', "
               + "telefono = '" + paciente.getTelefono() + "', "
               + "oidplan = '"+ paciente.getOidPlan()+ "' "
               + "WHERE oidpaciente = '" + paciente.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      PacienteAgente paciente;

      try {
         while(resultado.next()){
         paciente = new PacienteAgente();

         paciente.setImplementacion(new PacienteImpl());
         paciente.setOid(resultado.getString("oidpaciente"));
         paciente.setNumPaciente(resultado.getInt("numero_paciente"));
         paciente.setNombre(resultado.getString("nombre_paciente"));
         paciente.setDni(resultado.getInt("dni"));
         paciente.setDomicilio(resultado.getString("domicilio"));
         paciente.setTelefono(resultado.getString("telefono"));
         paciente.setOidPlan(resultado.getString("oidplan"));

         lista.add(paciente);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRPaciente - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      PacienteAgente paciente = new PacienteAgente();
      paciente.setImplementacion(new PacienteImpl());
      
      return paciente;
   } // fin del método nuevo
} // fin de la clase IPRPaciente