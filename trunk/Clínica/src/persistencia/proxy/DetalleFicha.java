package persistencia.proxy;
/**
 *
 * @author Matías
 */
public interface DetalleFicha{

   public FichaInternacion getFichaInternacion();

   public void setFichaInternacion(FichaInternacion fichaInternacion);

   public ServicioEspecial getServicioEspecial();

   public void setServicioEspecial(ServicioEspecial servicioEspecial);

   public int getCantidad();

   public void setCantidad(int cantidad);
}