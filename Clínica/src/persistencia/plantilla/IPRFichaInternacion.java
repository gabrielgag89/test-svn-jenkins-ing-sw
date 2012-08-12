package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.criterios.Criterio;
import persistencia.proxy.FichaInternacionAgente;
import persistencia.proxy.FichaInternacionImpl;
import persistencia.proxy.ObjetoPersistente;
import util.ServiciosTiempo;

public class IPRFichaInternacion extends IntermPersistenciaDBR{
   public IPRFichaInternacion(){
      this.mapeoAtributos.put("oid", "oidficha_internacion");
      this.mapeoAtributos.put("nroFicha", "numero_ficha_internacion");
      this.mapeoAtributos.put("fechaCreacion", "fecha");
      this.mapeoAtributos.put("prestacion", "oidprestacion");
      this.mapeoAtributos.put("estadoFichaInternacion", "oidestado_ficha_internacion");
      this.mapeoAtributos.put("cama", "oidcama");
      this.mapeoAtributos.put("paciente", "oidpaciente");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM ficha_internacion";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM ficha_internacion WHERE ";
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
      return "SELECT * FROM ficha_internacion WHERE oidficha_internacion = '" + oid  + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      FichaInternacionAgente ficha = (FichaInternacionAgente)objeto;

      return "INSERT INTO ficha_internacion " +
               "VALUES ('" + ficha.getOid() + "', '"
                           + ficha.getNroFicha() + "', '"
                           + ServiciosTiempo.getInstancia().dateToString(ficha.getFechaCreacion()) + "', '"
                           + ficha.getOidprestacion() + "', '"
                           + ficha.getOidestadoFichaInternacion() + "', '"
                           + ficha.getOidcama() + "', '"
                           + ficha.getOidpaciente() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      FichaInternacionAgente ficha = (FichaInternacionAgente)objeto;

      return "UPDATE ficha_internacion SET "
               + "numero_ficha_internacion = '" + ficha.getNroFicha() + "', "
               + "fecha = '" + ServiciosTiempo.getInstancia().dateToString(ficha.getFechaCreacion()) + "', "
               + "oidprestacion = '" + ficha.getOidprestacion() + "', "
               + "oidestado_ficha_internacion = '" + ficha.getOidestadoFichaInternacion() + "', "
               + "oidcama = '" + ficha.getOidcama() + "', "
               + "oidpaciente = '" + ficha.getOidpaciente() + "' "
               + "WHERE oidficha_internacion = '" + ficha.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista =  new ArrayList<ObjetoPersistente>();
      FichaInternacionAgente ficha;

      try {
         while (resultado.next()){
            ficha = new FichaInternacionAgente();
            
            ficha.setImplementacion(new FichaInternacionImpl());
            ficha.setOid(resultado.getString("oidficha_internacion"));
            ficha.setNroFicha(resultado.getInt("numero_ficha_internacion"));
            ficha.setFechaCreacion(resultado.getDate("fecha"));
            ficha.setOidprestacion(resultado.getString("oidprestacion"));
            ficha.setOidestadoFichaInternacion(resultado.getString("oidestado_ficha_internacion"));
            ficha.setOidcama(resultado.getString("oidcama"));
            ficha.setOidpaciente(resultado.getString("oidpaciente"));

            lista.add(ficha);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRFichaInternacion - convertirAObjeto - SQLException: "+ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      FichaInternacionAgente ficha = new FichaInternacionAgente();
      ficha.setImplementacion(new FichaInternacionImpl());

      return (ObjetoPersistente) ficha;
   } // fin del método nuevo
} // fin de la clase IPRFichaInternacion