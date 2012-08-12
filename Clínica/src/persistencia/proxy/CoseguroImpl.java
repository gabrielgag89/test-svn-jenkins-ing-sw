package persistencia.proxy;

public class CoseguroImpl implements Coseguro{
   private int codigoCoseguro;
   private double porcentaje;

   @Override
   public int getCodigoCoseguro() {
      return codigoCoseguro;
   }

   @Override
   public void setCodigoCoseguro(int codigoCoseguro) {
      this.codigoCoseguro = codigoCoseguro;
   }

   @Override
   public double getPorcentaje() {
      return porcentaje;
   }

   @Override
   public void setPorcentaje(double porcentaje) {
      this.porcentaje = porcentaje;
   }  
} // fin de la clase CoseguroImpl