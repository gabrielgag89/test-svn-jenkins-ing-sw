package persistencia.proxy;

import persistencia.FachadaPersistenciaInterna;

public class PrestacionAgente extends ObjetoPersistente implements Prestacion{
   private PrestacionImpl impl;
   private boolean tipoPrestacion = false;
   private String oidTipoPrestacion;
   
   public void setImplementacion(PrestacionImpl impl){
      this.impl = impl;
   }

   @Override
   public int getCodigoPrestacion() {
      return this.impl.getCodigoPrestacion();
   }

   @Override
   public void setCodigoPrestacion(int codigoPrestacion) {
      this.impl.setCodigoPrestacion(codigoPrestacion);
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
   public TipoPrestacion getTipoPrestacion() {
      if(!this.tipoPrestacion){
         this.impl.setTipoPrestacion((TipoPrestacion) FachadaPersistenciaInterna.getInstancia().buscar("TipoPrestacion", this.oidTipoPrestacion));
         this.tipoPrestacion = true;
      }
      
      return this.impl.getTipoPrestacion();
   }

   @Override
   public void setTipoPrestacion(TipoPrestacion tipoPrestacion) {
      this.impl.setTipoPrestacion(tipoPrestacion);
      this.oidTipoPrestacion = ((ObjetoPersistente) tipoPrestacion).getOid();
   }

   public String getOidTipoPrestacion() {
      return oidTipoPrestacion;
   }

   public void setOidTipoPrestacion(String oidTipoPrestacion) {
      this.oidTipoPrestacion = oidTipoPrestacion;
   }
} // fin de la clase PrestacionAgente