package persistencia.proxy;

public class HabitacionImpl  implements Habitacion  {
   private int nroHabitacion;
   private Sector sector;
   private TipoHabitacion tipoHabitacion;

   @Override
   public int getNroHabitacion() {
      return this.nroHabitacion;
   }

   @Override
   public void setNroHabitacion(int nroHabitacion) {
      this.nroHabitacion=nroHabitacion;
   }

   @Override
   public Sector getSector() {
      return this.sector;
   }

   @Override
   public void setSector(Sector sector) {
      this. sector=sector;
   }

   @Override
   public TipoHabitacion getTipoHabitacion() {
      return this.tipoHabitacion;
   }

   @Override
   public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
      this.tipoHabitacion=tipoHabitacion;
   }
}