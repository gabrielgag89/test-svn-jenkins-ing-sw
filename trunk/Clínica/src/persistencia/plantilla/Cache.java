package persistencia.plantilla;

import java.util.HashMap;
import persistencia.proxy.ObjetoPersistente;

public class Cache {
   private static Cache instancia;
   private HashMap objetos = new HashMap();
   
   private Cache(){}
   
   public static Cache getInstancia(){
      if (instancia == null)
         instancia = new Cache();
      
      return instancia;
   } // fin del método getInstancia
   
   public void agregar(String oid, Object objeto){
      objetos.put(oid, objeto);
   } // fin del método agregar
   
   public boolean existe(String oid) {
      return objetos.containsKey(oid);
   } // fin del método existe
   
   public void quitar(String oid) {
      objetos.remove(oid);
   } // fin del método quitar
   
   public ObjetoPersistente obtener(String oid){
      if (existe(oid))
         return (ObjetoPersistente) objetos.get(oid);
      else 
         return null;
   } // fin del método obtener
} // fin de la clase Cache