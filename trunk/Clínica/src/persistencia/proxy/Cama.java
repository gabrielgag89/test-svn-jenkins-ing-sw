// Cama: Cama.java
//

package persistencia.proxy;

/**
 *
 * @author Gabriel
 */
public interface Cama {
   public int getNumCama();

   public void setNumCama(int numCama);

   public Habitacion getHabitacion();

   public void setHabitacion(Habitacion habitacion);

   public EstadoCama getEstadoCama();

   public void setEstadoCama(EstadoCama estadoCama);
} // fin de la clase Cama
