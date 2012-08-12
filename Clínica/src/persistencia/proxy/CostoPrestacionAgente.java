package persistencia.proxy;

import java.util.Date;
import persistencia.FachadaPersistenciaInterna;

public class CostoPrestacionAgente extends ObjetoPersistente implements CostoPrestacion {
   private CostoPrestacionImpl impl;
   private boolean prestacion = false;
   private String oidPrestacion;

   public void setImplementacion(CostoPrestacionImpl impl) {
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
   public double getMonto() {
      return this.impl.getMonto();
   }

   @Override
   public void setMonto(double monto) {
      this.impl.setMonto(monto);
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

   public String getOidPrestacion() {
      return oidPrestacion;
   }

   public void setOidPrestacion(String oidPrestacion) {
      this.oidPrestacion = oidPrestacion;
   }
} // fin de la clase CostoPrestacionAgente