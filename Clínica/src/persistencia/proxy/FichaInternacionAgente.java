package persistencia.proxy;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import persistencia.FachadaPersistenciaInterna;
import persistencia.criterios.Criterio;

public class FichaInternacionAgente extends ObjetoPersistente implements FichaInternacion{
   private FichaInternacionImpl impl;
   private boolean prestacion = false;
   private boolean estadoFichaInternacion = false;
   private boolean cama = false;
   private boolean paciente = false;
   private boolean detalleFicha = false;
   private String oidPrestacion;
   private String oidEstadoFichaInternacion;
   private String oidCama;
   private String oidPaciente;

   public void setImplementacion(FichaInternacionImpl impl) {
      this.impl = impl;
   }

   @Override
   public int getNroFicha() {
      return this.impl.getNroFicha();
   }

   @Override
   public void setNroFicha(int nroFicha) {
      this.impl.setNroFicha(nroFicha);
   }

   @Override
   public Date getFechaCreacion() {
      return this.impl.getFechaCreacion();
   }

   @Override
   public void setFechaCreacion(Date fechaCreacion) {
      this.impl.setFechaCreacion(fechaCreacion);
   }

   @Override
   public Prestacion getPrestacion() {
      if(!prestacion){
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
   public EstadoFichaInternacion getEstadoFichaInternacion() {
      if(!estadoFichaInternacion){
         this.impl.setEstadoFichaInternacion((EstadoFichaInternacion) FachadaPersistenciaInterna.getInstancia().buscar("EstadoFichaInternacion", this.oidEstadoFichaInternacion));
         this.estadoFichaInternacion = true;
      }
      
      return this.impl.getEstadoFichaInternacion();
   }

   @Override
   public void setEstadoFichaInternacion(EstadoFichaInternacion estadoFichaInternacion) {
      this.impl.setEstadoFichaInternacion(estadoFichaInternacion);
      this.oidEstadoFichaInternacion = ((ObjetoPersistente) estadoFichaInternacion).getOid();
   }

   @Override
   public Cama getCama() {
      if(!cama){
         this.impl.setCama((Cama) FachadaPersistenciaInterna.getInstancia().buscar("Cama", this.oidCama));
         this.cama = true;
      }
      
      return this.impl.getCama();
   }

   @Override
   public void setCama(Cama cama) {
      this.impl.setCama(cama);
      this.oidCama = ((ObjetoPersistente) cama).getOid();
   }

   @Override
   public Paciente getPaciente() {
      if(!paciente){
         this.impl.setPaciente((Paciente) FachadaPersistenciaInterna.getInstancia().buscar("Paciente", this.oidPaciente));
         this.paciente = true;
      }
      
      return this.impl.getPaciente();
   }

   @Override
   public void setPaciente(Paciente paciente) {
      this.impl.setPaciente(paciente);
      this.oidPaciente = ((ObjetoPersistente) paciente).getOid();
   }

   @Override
   public List<DetalleFicha> getDetalleFicha() {
      if(!detalleFicha){
         List<Criterio> lista = new ArrayList<Criterio>();
         Criterio c = FachadaPersistenciaInterna.getInstancia().getCriterio("fichaInternacion", "=", this.getOid(), "");
         lista.add(c);
         this.impl.setDetalleFicha(FachadaPersistenciaInterna.getInstancia().buscar("DetalleFicha", lista));
         this.detalleFicha = true;
      }
      
      return this.impl.getDetalleFicha();
   }

   @Override
   public void setDetalleFicha(List<DetalleFicha> ListaDetalleFicha) {
      this.impl.setDetalleFicha(ListaDetalleFicha);
   }

   @Override
   public void addDetalleFicha(DetalleFicha detalleFicha) {
      this.impl.addDetalleFicha(detalleFicha);
   }

   @Override
   public boolean deleteDetalleFicha(DetalleFicha detalleFicha) {
      return this.impl.deleteDetalleFicha(detalleFicha);
   }

   public String getOidcama() {
      return oidCama;
   }

   public void setOidcama(String oidCama) {
      this.oidCama = oidCama;
   }

   public String getOidestadoFichaInternacion() {
      return oidEstadoFichaInternacion;
   }

   public void setOidestadoFichaInternacion(String oidEstadoFichaInternacion) {
      this.oidEstadoFichaInternacion = oidEstadoFichaInternacion;
   }

   public String getOidpaciente() {
      return oidPaciente;
   }

   public void setOidpaciente(String oidPaciente) {
      this.oidPaciente = oidPaciente;
   }

   public String getOidprestacion() {
      return oidPrestacion;
   }

   public void setOidprestacion(String oidPrestacion) {
      this.oidPrestacion = oidPrestacion;
   }
} // fin de la clase FichaInternacionAgente