package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;
import persistencia.proxy.ReciboAgente;
import persistencia.proxy.ReciboImpl;
import util.ServiciosTiempo;

public class IPRRecibo extends IntermPersistenciaDBR {
   public IPRRecibo(){
      this.mapeoAtributos.put("oid", "oidrecibo");
      this.mapeoAtributos.put("nroRecibo", "numero_recibo");
      this.mapeoAtributos.put("fecha", "fecha");
      this.mapeoAtributos.put("facturaPaciente", "oidfactura_paciente");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM recibo";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM recibo WHERE ";
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
      return "SELECT * FROM recibo WHERE oidrecibo = '" + oid  + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      ReciboAgente recibo = (ReciboAgente)objeto;
      
      return "INSERT INTO recibo "
                  + "VALUES ('" + recibo.getOid() + "', "
                                + recibo.getNroRecibo() + ", '"
                                + ServiciosTiempo.getInstancia().dateToString(recibo.getFecha()) + "', '"
                                + recibo.getOidFacturaPaciente() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      ReciboAgente recibo = (ReciboAgente)objeto;
      
      return "UPDATE recibo SET "
                  + "numero_recibo = " +  recibo.getNroRecibo() + ", "
                  + "fecha = '" + ServiciosTiempo.getInstancia().dateToString(recibo.getFecha()) + ", "
                  + "oidfactura_paciente = '" + recibo.getOidFacturaPaciente() + "' "
                  + "WHERE oidrecibo = '" + recibo.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista =  new ArrayList<ObjetoPersistente>();
      ReciboAgente recibo;
      
      try{
         while (resultado.next()){
            recibo = new ReciboAgente();
            recibo.setImplementacion(new ReciboImpl());
            recibo.setOid(resultado.getString("oidrecibo"));
            recibo.setNroRecibo(resultado.getInt("numero_recibo"));
            recibo.setFecha(resultado.getDate("fecha"));
            recibo.setOidFacturaPaciente(resultado.getString("oidfactura_paciente"));
            
            lista.add(recibo);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch(SQLException ex) {
         System.out.println("IPRRecibo - convertirAObjeto - SQLException: "+ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna
      
      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      ReciboAgente recibo = new ReciboAgente();
      recibo.setImplementacion(new ReciboImpl());
      
      return (ObjetoPersistente) recibo;
   } // fin del método nuevo
} // fin de la clase IPRRecibo