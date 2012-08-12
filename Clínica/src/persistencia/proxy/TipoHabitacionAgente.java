package persistencia.proxy;

public class TipoHabitacionAgente extends ObjetoPersistente implements TipoHabitacion {
   private TipoHabitacionImpl impl;

   public void setImplementacion(TipoHabitacionImpl impl) {
      this.impl = impl;
   }

   @Override
   public int getCodigoTipoHabitacion() {
      return this.impl.getCodigoTipoHabitacion();
   }

   @Override
   public void setCodigoTipoHabitacion(int codigoTipoHabitacion) {
      this.impl.setCodigoTipoHabitacion(codigoTipoHabitacion);
   }

   @Override
   public String getNombreTipo() {
      return this.impl.getNombreTipo();
   }

   @Override
   public void setNombreTipo(String nombreTipo) {
      this.impl.setNombreTipo(nombreTipo);
   }
} // fin de la clase TipoHabitacionAgente