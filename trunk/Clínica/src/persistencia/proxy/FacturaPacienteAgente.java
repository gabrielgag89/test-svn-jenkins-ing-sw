package persistencia.proxy;

import java.util.Date;
import persistencia.FachadaPersistenciaInterna;

public class FacturaPacienteAgente extends ObjetoPersistente implements FacturaPaciente{
   private FacturaPacienteImpl impl;
   private boolean fichaInternacion = false;
   private boolean estadoFacturaPaciente = false;
   private String oidFichaInternacion;
   private String oidEstadoFacturaPaciente;
   
   public void setImplementacion(FacturaPacienteImpl impl){
      this.impl = impl;
   }

   @Override
   public int getNumFactura() {
      return this.impl.getNumFactura();
   }

   @Override
   public void setNumFactura(int numFactura) {
      this.impl.setNumFactura(numFactura);
   }

   @Override
   public Date getFechaEmision() {
      return this.impl.getFechaEmision();
   }

   @Override
   public void setFechaEmision(Date fechaEmision) {
      this.impl.setFechaEmision(fechaEmision);
   }

   @Override
   public double getMonto() {
      return this.impl.getMonto();
   }

   @Override
   public void setMonto(double monto) {
      this.impl.setMonto(monto);
   }

   @Override
   public FichaInternacion getFichaInternacion() {
      if(!this.fichaInternacion){
         this.impl.setFichaInternacion((FichaInternacion) FachadaPersistenciaInterna.getInstancia().buscar("FichaInternacion", this.oidFichaInternacion));
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
   public EstadoFacturaPaciente getEstadoFacturaPaciente() {
      if(!this.estadoFacturaPaciente){
         this.impl.setEstadoFacturaPaciente((EstadoFacturaPaciente) FachadaPersistenciaInterna.getInstancia().buscar("EstadoFacturaPaciente", this.oidEstadoFacturaPaciente));
         this.estadoFacturaPaciente = true;
      }
      
      return this.impl.getEstadoFacturaPaciente();
   }

   @Override
   public void setEstadoFacturaPaciente(EstadoFacturaPaciente estadoFacturaPaciente) {
      this.impl.setEstadoFacturaPaciente(estadoFacturaPaciente);
      this.oidEstadoFacturaPaciente = ((ObjetoPersistente) estadoFacturaPaciente).getOid();
   }

   public String getOidFichaInternacion() {
      return oidFichaInternacion;
   }

   public void setOidFichaInternacion(String oidFichaInternacion) {
      this.oidFichaInternacion = oidFichaInternacion;
   }

   public String getOidEstadoFacturaCliente() {
      return oidEstadoFacturaPaciente;
   }

   public void setOidEstadoFacturaPaciente(String oidEstadoFacturaPaciente) {
      this.oidEstadoFacturaPaciente = oidEstadoFacturaPaciente;
   }
} // fin de la clase FacturaPacienteAgente