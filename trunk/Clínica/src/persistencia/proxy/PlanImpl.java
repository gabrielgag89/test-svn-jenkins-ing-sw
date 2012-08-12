package persistencia.proxy;

public class PlanImpl implements Plan {
   private int codigoPlan;
   private String descripcion;
   public ObraSocial obraSocial;

   @Override
   public int getCodigoPlan() {
      return codigoPlan;
   }

   @Override
   public void setCodigoPlan(int codigoPlan) {
      this.codigoPlan = codigoPlan;
   }

   @Override
   public String getDescripcion() {
      return descripcion;
   }

   @Override
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   @Override
   public ObraSocial getObraSocial() {
      return this.obraSocial;
   }

   @Override
   public void setObraSocial(ObraSocial obraSocial) {
      this.obraSocial = obraSocial;
   }
} // fin de la clase PlanImpl