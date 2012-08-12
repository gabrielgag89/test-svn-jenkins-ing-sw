package persistencia.proxy;

import java.util.Date;

public class CostoPrestacionImpl implements CostoPrestacion {
   private Date fechaInicio;
   private Date fechaFin;
   private double monto;
   private Prestacion prestacion;

   @Override
   public Date getFechaInicio() {
      return fechaInicio;
   }

   @Override
   public void setFechaInicio(Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Override
   public Date getFechaFin() {
      return fechaFin;
   }

   @Override
   public void setFechaFin(Date fechaFin) {
      this.fechaFin = fechaFin;
   }

   @Override
   public double getMonto() {
      return monto;
   }

   @Override
      public void setMonto(double monto) {
   this.monto = monto;
   }

   @Override
   public Prestacion getPrestacion() {
      return prestacion;
   }

   @Override
   public void setPrestacion(Prestacion prestacion) {
      this.prestacion = prestacion;
   }
}//end CostoPrestacion