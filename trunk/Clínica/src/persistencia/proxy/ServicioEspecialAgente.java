package persistencia.proxy;

public class ServicioEspecialAgente extends ObjetoPersistente implements ServicioEspecial{
   private ServicioEspecialImpl impl;
   
   public void setImplementacion(ServicioEspecialImpl impl){
      this.impl = impl;
   }

   @Override
   public int getCodigoServicio() {
      return this.impl.getCodigoServicio();
   }

   @Override
   public void setCodigoServicio(int codigoServicio) {
      this.impl.setCodigoServicio(codigoServicio);
   }

   @Override
   public String getNombreServicio() {
      return this.impl.getNombreServicio();
   }

   @Override
   public void setNombreServicio(String nombreServicio) {
      this.impl.setNombreServicio(nombreServicio);
   }
} // fin de la clase ServicioEspecialAgente