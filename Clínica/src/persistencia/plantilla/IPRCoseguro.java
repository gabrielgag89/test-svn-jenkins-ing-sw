package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.criterios.Criterio;
import persistencia.proxy.ObjetoPersistente;
import persistencia.proxy.CoseguroAgente;
import persistencia.proxy.CoseguroImpl;

public class IPRCoseguro extends IntermPersistenciaDBR{
   public IPRCoseguro(){
      this.mapeoAtributos.put("oid", "oidcoseguro");
      this.mapeoAtributos.put("codigoCoseguro", "codigo_coseguro");
      this.mapeoAtributos.put("porcentaje", "porcentaje");
   } // fin del constructor

   @Override
   public String select() {
      return "SELECT * FROM coseguro";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM coseguro WHERE ";
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
      return "SELECT * FROM coseguro WHERE oidcoseguro ='" + oid +"'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      CoseguroAgente coseguro = (CoseguroAgente) objeto;
      
      return "INSERT INTO coseguro "
                  + "VALUES ('" + coseguro.getOid() + "', "
                                + coseguro.getCodigoCoseguro() + ", "
                                + coseguro.getPorcentaje();
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      CoseguroAgente coseguro = (CoseguroAgente)objeto;
      
      return "UPDATE coseguro SET "
                  + "codigo = '" + coseguro.getCodigoCoseguro() + "', "  
                  + "porcentaje = " + coseguro.getPorcentaje() + " "
                  + "WHERE oidcoseguro = '" + coseguro.getOid() + "'";  
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      CoseguroAgente coseguro;

      try {
         while(resultado.next()){
            coseguro = new CoseguroAgente();
            
            coseguro.setImplementacion(new CoseguroImpl());
            coseguro.setOid(resultado.getString("oidcoseguro"));
            coseguro.setCodigoCoseguro(resultado.getInt("codigo_coseguro"));
            coseguro.setPorcentaje(resultado.getDouble("porcentaje"));

            lista.add(coseguro);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRCoseguro - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      CoseguroAgente coseguro = new CoseguroAgente();
      coseguro.setImplementacion(new CoseguroImpl());

      return coseguro;
   } // fin del método nuevo
} // fin de la clase IPRCoseguro