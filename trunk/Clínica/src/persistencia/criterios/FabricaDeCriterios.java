package persistencia.criterios;

public class FabricaDeCriterios {
   private static FabricaDeCriterios instancia;
   
   private FabricaDeCriterios(){}
   
   public static FabricaDeCriterios getInstancia(){
      if (instancia == null)
         instancia = new FabricaDeCriterios();
      
      return instancia;
   } // fin del método getInstancia
   
   public Criterio getCriterio(String atributo, String operador, Object valor, String opLogico) {
      return new Criterio(atributo, operador, valor, opLogico);
   } // fin del método getCriterio
} // fin de la clase FabricaDeCriterios