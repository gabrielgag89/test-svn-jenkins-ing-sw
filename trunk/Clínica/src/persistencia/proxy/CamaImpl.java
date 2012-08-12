package persistencia.proxy;

public class CamaImpl implements Cama{
   private int numCama;
   private Habitacion habitacion;
   private EstadoCama estadoCama;

   @Override
   public int getNumCama() {
      return numCama;
   }

   @Override
   public void setNumCama(int numCama) {
      this.numCama = numCama;
   }

   @Override
   public Habitacion getHabitacion() {
      return habitacion;
   }

   @Override
   public void setHabitacion(Habitacion habitacion) {
      this.habitacion = habitacion;
   }

   @Override
   public EstadoCama getEstadoCama() {
      return estadoCama;
   }

   @Override
   public void setEstadoCama(EstadoCama estadoCama) {
      this.estadoCama = estadoCama;
   }
}//end Cama