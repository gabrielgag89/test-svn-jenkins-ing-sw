package persistencia.proxy;

public class ServicioEspecialImpl implements ServicioEspecial{
   private int codigoServicio;
   private String nombreServicio;

   @Override
   public int getCodigoServicio() {
      return codigoServicio;
   }

   @Override
   public void setCodigoServicio(int codigoServicio) {
      this.codigoServicio = codigoServicio;
   }

   @Override
   public String getNombreServicio() {
      return nombreServicio;
   }

   @Override
   public void setNombreServicio(String nombreServicio) {
      this.nombreServicio = nombreServicio;
   }
} // fin de la clase ServicioEspecialImpl