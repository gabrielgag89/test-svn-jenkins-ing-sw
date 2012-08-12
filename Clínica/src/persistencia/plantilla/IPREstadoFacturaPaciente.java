package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.EstadoFacturaPacienteAgente;
import persistencia.proxy.EstadoFacturaPacienteImpl;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;

public class IPREstadoFacturaPaciente extends IntermPersistenciaDBR{
   public IPREstadoFacturaPaciente(){
      this.mapeoAtributos.put("oid", "oidestado_factura_paciente");
      this.mapeoAtributos.put("nombreEstado", "nombre_estado_factura_paciente");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM estado_factura_paciente";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM estado_factura_paciente WHERE ";
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
      return "SELECT * FROM estado_factura_paciente WHERE oidestado_factura_paciente = '" + oid +"'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      EstadoFacturaPacienteAgente estado = (EstadoFacturaPacienteAgente) objeto;

      return "INSERT INTO estado_factura_paciente "
                  + "VALUES ('" + estado.getOid() + "', '"
                                + estado.getNombreEstado() + "'";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      EstadoFacturaPacienteAgente estado = (EstadoFacturaPacienteAgente)objeto;

      return "UPDATE estado_factura_paciente SET "
               + "nombre_estado_factura_paciente = '" + estado.getNombreEstado() + "' "
               + "WHERE oidestado_factura_paciente = '" + estado.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      EstadoFacturaPacienteAgente estado;

      try {
         while(resultado.next()){
            estado = new EstadoFacturaPacienteAgente();

            estado.setImplementacion(new EstadoFacturaPacienteImpl());
            estado.setOid(resultado.getString("oidestado_factura_paciente"));
            estado.setNombreEstado(resultado.getString("nombre_estado_factura_paciente"));

            lista.add(estado);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPREstadoFacturaCliente - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      EstadoFacturaPacienteAgente estado = new EstadoFacturaPacienteAgente();
      estado.setImplementacion(new EstadoFacturaPacienteImpl());

      return estado;
   } // fin del método nuevo
} // fin de la clase IPREstadoFacturaPaciente