package persistencia.proxy;

import persistencia.FachadaPersistenciaInterna;

public class PlanAgente extends ObjetoPersistente implements Plan {
   private PlanImpl impl;
   private boolean obrasocial = false;
   private String oidObraSocial;

   public void setImplementacion(PlanImpl impl) {
      this.impl = impl;
   }

   @Override
   public int getCodigoPlan() {
      return this.impl.getCodigoPlan();
   }

   @Override
   public void setCodigoPlan(int codigoPlan) {
      this.impl.setCodigoPlan(codigoPlan);
   }

   @Override
   public String getDescripcion() {
      return this.impl.getDescripcion();
   }

   @Override
   public void setDescripcion(String descripcion) {
      this.impl.setDescripcion(descripcion);
   }

   @Override
   public ObraSocial getObraSocial() {
      if(!obrasocial){
         this.impl.setObraSocial((ObraSocial)FachadaPersistenciaInterna.getInstancia().buscar("ObraSocial",oidObraSocial));
         this.obrasocial = true;
      }
      
      return this.impl.getObraSocial();
   }       

   @Override
   public void setObraSocial(ObraSocial obrasocial) {
      this.impl.setObraSocial(obrasocial);
      this.oidObraSocial = ((ObjetoPersistente) obrasocial).getOid();
   }
   
   public String getOidObraSocial() {
      return oidObraSocial;
   }
   
   public void setOidObraSocial(String oidObraSocial) {
      this.oidObraSocial = oidObraSocial;
   }
} // fin de la clase PlanAgente