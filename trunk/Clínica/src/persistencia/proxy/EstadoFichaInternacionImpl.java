package persistencia.proxy;

public class EstadoFichaInternacionImpl implements EstadoFichaInternacion{
   private String nombreEstado;

   @Override
   public String getNombreEstado() {
      return nombreEstado;
   } // fin del método getNombreEstado

   @Override
   public void setNombreEstado(String nombreEstado) {
      this.nombreEstado = nombreEstado;
   } // fin del método setNombreEstado
} // fin de la clase EstadoFichaInternacionImpl