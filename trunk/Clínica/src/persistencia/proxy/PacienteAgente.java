package persistencia.proxy;

import persistencia.FachadaPersistenciaInterna;

public class PacienteAgente extends ObjetoPersistente implements Paciente{
   private PacienteImpl impl;
   private boolean plan = false;
   private String oidPlan;

   public void setImplementacion(PacienteImpl impl) {
      this.impl = impl;
   }

   @Override
   public int getNumPaciente() {
      return this.impl.getNumPaciente();
   }

   @Override
   public void setNumPaciente(int numPaciente) {
      this.impl.setNumPaciente(numPaciente);
   }

   @Override
   public String getNombre() {
      return this.impl.getNombre();
   }

   @Override
   public void setNombre(String nombreP) {
      this.impl.setNombre(nombreP);
   }

   @Override
   public int getDni() {
      return this.impl.getDni();
   }

   @Override
   public void setDni(int dni) {
      this.impl.setDni(dni);
   }

   @Override
   public String getDomicilio() {
      return this.impl.getDomicilio();
   }

   @Override
   public void setDomicilio(String domicilio) {
      this.impl.setDomicilio(domicilio);
   }

   @Override
   public String getTelefono() {
      return this.impl.getTelefono();
   }

   @Override
   public void setTelefono(String telefono) {
      this.impl.setTelefono(telefono);
   }

   @Override
   public Plan getPlan() {
      if(oidPlan == null)
         return null;
      
      if(!plan){
         this.impl.setPlan((Plan)FachadaPersistenciaInterna.getInstancia().buscar("Plan", oidPlan));        
         this.plan = true;
      }

      return this.impl.getPlan();
   }

   @Override
   public void setPlan(Plan plan) {
      this.impl.setPlan(plan);
      this.oidPlan = ((ObjetoPersistente) plan).getOid();
   }

   public String getOidPlan() {
      return oidPlan;
   }

   public void setOidPlan(String oidPlan) {
      this.oidPlan = oidPlan;
   }
} // fin de la clase 