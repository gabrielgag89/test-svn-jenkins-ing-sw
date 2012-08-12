package persistencia.proxy;

public class ObjetoPersistente {
   protected String oid;
   protected boolean nuevo;
   protected boolean limpio;
   
   public String getOid(){
      return oid;
   } // fin del método getOid
   
   public void setOid(String oid){
      this.oid = oid;
   } // fin del método setOid
   
   public boolean getNuevo(){
      return nuevo;
   } // fin del método getNuevo
   
   public void setNuevo(boolean nuevo){
      this.nuevo = nuevo;
   } // fin del método setNuevo
   
   @Override
   public String toString(){
      return this.oid;
   } // fin del método toString
} // fin de la clase ObjetoPersistente