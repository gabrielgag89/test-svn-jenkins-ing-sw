package persistencia.plantilla;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import persistencia.proxy.ObjetoPersistente;
import persistencia.criterios.Criterio;
import persistencia.proxy.SectorAgente;
import persistencia.proxy.SectorImpl;

public class IPRSector extends IntermPersistenciaDBR {
   public IPRSector(){
      this.mapeoAtributos.put("oid", "oidsector");
      this.mapeoAtributos.put("codigoSector", "codigo_sector");
      this.mapeoAtributos.put("descripcionSector", "descripcion_sector");
      this.mapeoAtributos.put("nroPiso", "numero_piso");
   } // fin del constructor
   
   @Override
   public String select() {
      return "SELECT * FROM sector";
   } // fin del método select

   @Override
   public String select(List<Criterio> criterios) {
      String sql = "SELECT * FROM sector WHERE ";
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
      return "SELECT * FROM sector WHERE oidsector = '" + oid + "'";
   } // fin del método select

   @Override
   public String insertar(Object objeto) {
      SectorAgente sect = (SectorAgente) objeto;

      return "INSERT INTO sector "
               + "VALUES ('" + sect.getOid() + "', "
                             + sect.getCodigoSector() + ", '"
                             + sect.getDescripcionSector() + "', "
                             + sect.getNumeroPisoSector() + ")";
   } // fin del método insertar

   @Override
   public String actualizar(Object objeto) {
      SectorAgente sect = (SectorAgente) objeto;

      return "UPDATE sector SET "
               + "codigo_sector = " + sect.getCodigoSector() + ", "
               + "descripcion_sector = '" + sect.getDescripcionSector() + "', "
               + "numero_piso = " + sect.getNumeroPisoSector() + " "
               + "WHERE oidsector =  '" + sect.getOid() + "'";
   } // fin del método actualizar

   @Override
   public List<ObjetoPersistente> convertirAObjeto(ResultSet resultado) {
      List<ObjetoPersistente> lista = new ArrayList<ObjetoPersistente>();
      SectorAgente sector;

      try {
         while(resultado.next()){
            sector = new SectorAgente();

            sector.setImplementacion(new SectorImpl());
            sector.setOid(resultado.getString("oidsector"));
            sector.setCodigoSector(resultado.getInt("codigo_sector"));
            sector.setDescripcionSector(resultado.getString("descripcion_sector"));
            sector.setNumeroPisoSector(resultado.getInt("numero_piso"));

            lista.add(sector);
         } // fin de while de creación de agentes
      } // fin de try de error en la obtención del valor de una columna
      catch (SQLException ex) {
         System.err.println("IPRSector - convertirAObjeto(ResultSet resultado) - " + ex.getMessage());
      } // fin de catch de error en la obtención del valor de una columna

      return lista;
   } // fin del método convertirAObjeto

   @Override
   public ObjetoPersistente nuevo() {
      SectorAgente sector = new SectorAgente();
      sector.setImplementacion(new SectorImpl());

      return sector;
   } // fin del método nuevo
} // fin de la clase IPRSector