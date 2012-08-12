package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.ObjetoPersistente;
import persistencia.proxy.PrestacionAgente;
import persistencia.proxy.PrestacionImpl;
import persistencia.criterios.Criterio;

public class IPRPrestacion extends IntermPersistenciaDBR{
   public IPRPrestacion(){
      this.mapeoAtributos.put("oid", "oidprestacion");
      this.mapeoAtributos.put("codigoPrestacion", "codigo_prestacion");
      this.mapeoAtributos.put("descripcion", "descripcion");
      this.mapeoAtributos.put("tipoPrestacion", "oidtipo_prestacion");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM prestacion";
   } // fin del método select
   
   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM prestacion WHERE ";
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
      return "SELECT * FROM prestacion WHERE oidprestacion = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      PrestacionAgente prest = (PrestacionAgente) objeto;
      return "INSERT INTO prestacion "
                  + "VALUES ('" + prest.getOid() + "', "
                                + prest.getCodigoPrestacion() + ", '"
                                + prest.getDescripcion() + "', '"
                                + prest.getOidTipoPrestacion() + "')";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      PrestacionAgente prest = (PrestacionAgente) objeto;
      
      return "UPDATE prestacion SET "
               + "codigo_prestacion = " + prest.getCodigoPrestacion() + ", "
               + "descripcion = '" + prest.getDescripcion() + "', "
               + "oidtipo_prestacion = '" + prest.getOidTipoPrestacion() + "' "
               + "WHERE oidprestacion =  '" + prest.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      PrestacionAgente prest;
      
      try {
         while(resultado.next()){
            prest = new PrestacionAgente();
            
            prest.setImplementacion(new PrestacionImpl());
            prest.setOid(resultado.getString("oidprestacion"));
            prest.setCodigoPrestacion(resultado.getInt("codigo_prestacion"));
            prest.setDescripcion(resultado.getString("descripcion"));
            prest.setOidTipoPrestacion(resultado.getString("oidtipo_prestacion"));
            
            lista.add(prest);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRPrestacion - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna
      
      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      PrestacionAgente prest = new PrestacionAgente();
      prest.setImplementacion(new PrestacionImpl());
      
      return prest;
   } // fin del método nuevo
} // fin de la clase IPRPrestacion