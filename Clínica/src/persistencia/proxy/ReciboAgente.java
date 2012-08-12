package persistencia.proxy;

import java.util.Date;
import persistencia.FachadaPersistenciaInterna;

public class ReciboAgente extends ObjetoPersistente implements Recibo {
   private ReciboImpl impl;
   private boolean facturaPaciente = false;
   private String oidFacturaPaciente;

   public void setImplementacion(ReciboImpl impl) {
      this.impl = impl;
   }

   @Override
   public int getNroRecibo() {
      return this.impl.getNroRecibo();
   }

   @Override
   public void setNroRecibo(int nroRecibo) {
      this.impl.setNroRecibo(nroRecibo);
   }

   @Override
   public Date getFecha() {
      return  this.impl.getFecha();
   }

   @Override
   public void setFecha(Date fecha) {
      this.impl.setFecha(fecha);
   }

   @Override
   public FacturaPaciente getFacturaPaciente() {
      if(!facturaPaciente){
         this.impl.setFacturaPaciente((FacturaPaciente) FachadaPersistenciaInterna.getInstancia().buscar("FacturaPaciente", this.oidFacturaPaciente));
         this.facturaPaciente = true;
      }
      
      return this.impl.getFacturaPaciente();
   }

   @Override
   public void setFacturaPaciente(FacturaPaciente facturaPaciente) {
      this.impl.setFacturaPaciente(facturaPaciente);
      this.oidFacturaPaciente = ((ObjetoPersistente) facturaPaciente).getOid();
   }

   public String getOidFacturaPaciente() {
      return oidFacturaPaciente;
   }

   public void setOidFacturaPaciente(String oidFacturaPaciente) {
      this.oidFacturaPaciente = oidFacturaPaciente;
   }
} // fin de la clase ReciboAgente