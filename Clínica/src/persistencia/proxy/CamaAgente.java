package persistencia.proxy;

import persistencia.FachadaPersistenciaInterna;

public class CamaAgente extends ObjetoPersistente implements Cama{
   private CamaImpl impl;
   private boolean habitacion = false;
   private boolean estadoCama = false;
   private String oidHabitacion;
   private String oidEstadoCama;

   public void setImplementacion(CamaImpl impl){
      this.impl = impl;
   }

   @Override
   public int getNumCama() {
      return this.impl.getNumCama();
   }

   @Override
   public void setNumCama(int numCama) {
      this.impl.setNumCama(numCama);
   }

   @Override
   public Habitacion getHabitacion() {
      if(!this.habitacion){
           this.impl.setHabitacion((Habitacion) FachadaPersistenciaInterna.getInstancia().buscar("Habitacion", this.oidHabitacion));
           this.habitacion = true;
       }
      
       return this.impl.getHabitacion();
   }

   @Override
   public void setHabitacion(Habitacion habitacion) {
      this.impl.setHabitacion(habitacion);
      this.oidHabitacion = ((ObjetoPersistente) habitacion).getOid();
   }

   @Override
   public EstadoCama getEstadoCama() {
       if(!this.estadoCama){
           this.impl.setEstadoCama((EstadoCama) FachadaPersistenciaInterna.getInstancia().buscar("EstadoCama", this.oidEstadoCama));
           this.estadoCama = true;
       }
       
       return this.impl.getEstadoCama();
   }

   @Override
   public void setEstadoCama(EstadoCama estadoCama) {
      this.impl.setEstadoCama(estadoCama);
      this.oidEstadoCama = ((ObjetoPersistente) estadoCama).getOid();
   }
   
   public String getOidHabitacion(){
       return oidHabitacion;
   }
   
   public void setOidHabitacion(String oidHabitacion){
       this.oidHabitacion = oidHabitacion;
   }
   
   public String getOidEstadoCama(){
       return oidEstadoCama;
   }
   
   public void setOidEstadoCama(String oidEstadoCama){
       this.oidEstadoCama = oidEstadoCama;
   }
} // fin de la clase CamaAgente