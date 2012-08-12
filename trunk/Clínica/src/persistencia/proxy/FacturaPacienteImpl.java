package persistencia.proxy;

import java.util.Date;

public class FacturaPacienteImpl implements FacturaPaciente{
   private int numFactura;
   private Date fechaEmision;
   private double monto;
   public FichaInternacion fichaInternacion;
   public EstadoFacturaPaciente estadoFacturaPaciente;

   @Override
   public int getNumFactura() {
      return numFactura;
   }

   @Override
   public void setNumFactura(int numFactura) {
      this.numFactura = numFactura;
   }

   @Override
   public Date getFechaEmision() {
      return fechaEmision;
   }

   @Override
   public void setFechaEmision(Date fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   @Override
   public double getMonto() {
      return monto;
   }

   @Override
   public void setMonto(double monto) {
      this.monto = monto;
   }

   @Override
   public FichaInternacion getFichaInternacion() {
      return fichaInternacion;
   }

   @Override
   public void setFichaInternacion(FichaInternacion fichaInternacion) {
      this.fichaInternacion = fichaInternacion;
   }

   @Override
   public EstadoFacturaPaciente getEstadoFacturaPaciente() {
      return estadoFacturaPaciente;
   }

   @Override
   public void setEstadoFacturaPaciente(EstadoFacturaPaciente estadoFacturaCliente) {
      this.estadoFacturaPaciente = estadoFacturaCliente;
   }
} // fin de la clase FacturaPacienteImpl