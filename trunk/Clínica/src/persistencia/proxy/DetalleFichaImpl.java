package persistencia.proxy;

public class DetalleFichaImpl implements DetalleFicha {
    private int cantidad;
    private FichaInternacion fichaInternacion;
    private ServicioEspecial servicioEspecial;

   @Override
   public int getCantidad() {
      return cantidad;
   }

   @Override
   public void setCantidad(int cantidad) {
      this.cantidad = cantidad;
   }

    @Override
    public FichaInternacion getFichaInternacion() {
        return fichaInternacion;
    }

    @Override
    public void setFichaInternacion(FichaInternacion fichaInternacion) {
        this.fichaInternacion = fichaInternacion;
    }

    @Override
    public ServicioEspecial getServicioEspecial() {
        return servicioEspecial;
    }

    @Override
    public void setServicioEspecial(ServicioEspecial servicioEspecial) {
        this.servicioEspecial = servicioEspecial;
    }
} // fin de la clase DetalleFichaImpl