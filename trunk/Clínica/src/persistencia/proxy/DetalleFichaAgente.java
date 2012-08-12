package persistencia.proxy;

import persistencia.FachadaPersistenciaInterna;

public class DetalleFichaAgente extends ObjetoPersistente implements DetalleFicha {
   private DetalleFichaImpl impl;
   private boolean fichaInternacion = false;
   private boolean servicioEspecial = false;
   private String oidFichaInternacion;
   private String oidServicioEspecial;

   public void setImplementacion(DetalleFichaImpl impl){
      this.impl = impl;
   }

   @Override
   public int getCantidad() {
      return this.impl.getCantidad();
   }

   @Override
   public void setCantidad(int cantidad) {
      this.impl.setCantidad(cantidad);
   }

   @Override
   public FichaInternacion getFichaInternacion() {
      if(!this.fichaInternacion){
         this.impl.setFichaInternacion((FichaInternacion) FachadaPersistenciaInterna.getInstancia().buscar("FichaInternacion",this.oidFichaInternacion));
         this.fichaInternacion = true;
      }
      
      return this.impl.getFichaInternacion();
   }

   @Override
   public void setFichaInternacion(FichaInternacion fichaInternacion) {
      this.impl.setFichaInternacion(fichaInternacion);
      this.oidFichaInternacion = ((ObjetoPersistente) fichaInternacion).getOid();
   }

   @Override
   public ServicioEspecial getServicioEspecial(){
      if(!this.servicioEspecial){
         this.impl.setServicioEspecial((ServicioEspecial)FachadaPersistenciaInterna.getInstancia().buscar("ServicioEspecial", this.oidServicioEspecial));
         this.servicioEspecial = true;
      }
      
      return this.impl.getServicioEspecial();
   }

   @Override
   public void setServicioEspecial(ServicioEspecial servicioEspecial){
      this.impl.setServicioEspecial(servicioEspecial);
      this.oidServicioEspecial = ((ObjetoPersistente) servicioEspecial).getOid();
   }

   public String getOidFichaInternacion(){
      return oidFichaInternacion;
   }

   public void setOidFichaInterncaion(String oidFichaInternacion){
      this.oidFichaInternacion = oidFichaInternacion;
   }

   public String getOidServicioEspecial(){
      return oidServicioEspecial;
   }

   public void setOidServicioEspecial(String oidServicioEspecial){
      this.oidServicioEspecial = oidServicioEspecial;
   }
} // fin de la clase DetalleFichaAgente