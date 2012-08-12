// Prestacion: Prestacion.java
//

package persistencia.proxy;

/**
 *
 * @author Gabriel
 */
public interface Prestacion {
   public int getCodigoPrestacion();

   public void setCodigoPrestacion(int codigoPrestacion);
   
   public String getDescripcion();

   public void setDescripcion(String descripcion);

   public TipoPrestacion getTipoPrestacion();
   
   public void setTipoPrestacion(TipoPrestacion tipoPrestacion);
} // fin de la clase Prestacion