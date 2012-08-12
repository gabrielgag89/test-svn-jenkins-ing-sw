package persistencia.proxy;

import java.util.Date;
import persistencia.FachadaPersistenciaInterna;

public class ConvenioAgente extends ObjetoPersistente implements Convenio{
   private ConvenioImpl impl;
   private boolean plan = false;
   private boolean prestacion = false;
   private boolean coseguro = false;
   private String oidPlan;
   private String oidPrestacion;
   private String oidCoseguro;
   
   public void setImplementacion(ConvenioImpl impl){
      this.impl = impl;
   }

   @Override
   public Date getFechaInicio() {
      return this.impl.getFechaInicio();
   }

   @Override
   public void setFechaInicio(Date fechaInicio) {
      this.impl.setFechaInicio(fechaInicio);
   }

   @Override
   public Date getFechaFin() {
      return this.impl.getFechaFin();
   }

   @Override
   public void setFechaFin(Date fechaFin) {
      this.impl.setFechaFin(fechaFin);
   }

   @Override
   public Plan getPlan() {
      if(!this.plan){
         this.impl.setPlan((Plan) FachadaPersistenciaInterna.getInstancia().buscar("Plan", this.oidPlan));
         this.plan = true;
      }
      
      return this.impl.getPlan();
   }

   @Override
   public void setPlan(Plan plan) {
      this.impl.setPlan(plan);
      this.oidPlan = ((ObjetoPersistente) plan).getOid();
   }

   @Override
   public Prestacion getPrestacion() {
      if(!this.prestacion){
         this.impl.setPrestacion((Prestacion) FachadaPersistenciaInterna.getInstancia().buscar("Prestacion", this.oidPrestacion));
         this.prestacion = true;
      }
      
      return this.impl.getPrestacion();
   }

   @Override
   public void setPrestacion(Prestacion prestacion) {
      this.impl.setPrestacion(prestacion);
      this.oidPrestacion = ((ObjetoPersistente) prestacion).getOid();
   }

   @Override
   public Coseguro getCoseguro() {
      if(!this.coseguro){
         this.impl.setCoseguro((Coseguro) FachadaPersistenciaInterna.getInstancia().buscar("Coseguro", this.oidCoseguro));
         this.coseguro = true;
      }
      
      return this.impl.getCoseguro();
   }

   @Override
   public void setCoseguro(Coseguro coseguro) {
      this.impl.setCoseguro(coseguro);
      this.oidCoseguro = ((ObjetoPersistente) coseguro).getOid();
   }

   public String getOidPlan() {
      return oidPlan;
   }

   public void setOidPlan(String oidPlan) {
      this.oidPlan = oidPlan;
   }

   public String getOidPrestacion() {
      return oidPrestacion;
   }

   public void setOidPrestacion(String oidPrestacion) {
      this.oidPrestacion = oidPrestacion;
   }

   public String getOidCoseguro() {
      return oidCoseguro;
   }

   public void setOidCoseguro(String oidCoseguro) {
      this.oidCoseguro = oidCoseguro;
   }
} // fin de la clase ConvenioAgente