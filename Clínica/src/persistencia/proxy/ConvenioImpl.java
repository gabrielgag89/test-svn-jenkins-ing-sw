package persistencia.proxy;

import java.util.Date;

public class ConvenioImpl implements Convenio{
   private Date fechaInicio;
   private Date fechaFin;
   public Plan plan;
   public Prestacion prestacion;
   public Coseguro coseguro;

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
   public Plan getPlan() {
      return plan;
   }

   @Override
   public void setPlan(Plan plan) {
      this.plan = plan;
   }

   @Override
   public Prestacion getPrestacion() {
      return prestacion;
   }

   @Override
   public void setPrestacion(Prestacion prestacion) {
      this.prestacion = prestacion;
   }

   @Override
   public Coseguro getCoseguro() {
      return coseguro;
   }

   @Override
   public void setCoseguro(Coseguro coseguro) {
      this.coseguro = coseguro;
   }
} // fin de la clase ConvenioImpl