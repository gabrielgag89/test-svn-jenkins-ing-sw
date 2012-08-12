package persistencia.proxy;

public class EstadoFichaInternacionAgente extends ObjetoPersistente implements EstadoFichaInternacion {
   private EstadoFichaInternacionImpl impl;

   public void setImplementacion(EstadoFichaInternacionImpl impl){
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
} // fin de la clase EstadoFichaInternacionAgente