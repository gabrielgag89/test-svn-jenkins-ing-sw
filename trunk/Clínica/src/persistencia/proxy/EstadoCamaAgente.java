package persistencia.proxy;

public class EstadoCamaAgente extends ObjetoPersistente implements EstadoCama {
   private EstadoCamaImpl impl;

   public void setImplementacion(EstadoCamaImpl impl) {
      this.impl = impl;
   }  

   @Override
   public String getNombreEstado() {
      return this.impl.getNombreEstado();
   }

   @Override
   public void setNombreEstado(String nombreEstado) {
      this.impl.setNombreEstado(nombreEstado);
   }   
} // fin de la clase EstadoCamaAgente