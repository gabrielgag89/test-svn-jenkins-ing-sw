package persistencia.proxy;

import java.util.Date;

public class ReciboImpl implements Recibo{
   private int nroRecibo;
   private Date fecha;
   private FacturaPaciente facturaPaciente;

   @Override
   public int getNroRecibo() {
      return this.nroRecibo;
   }

   @Override
   public void setNroRecibo(int nroRecibo) {
      this.nroRecibo = nroRecibo;
   }

   @Override
   public Date getFecha() {
      return this.fecha;
   }

   @Override
   public void setFecha(Date fecha) {
      this.fecha = fecha;
   }

   @Override
   public FacturaPaciente getFacturaPaciente() {
      return this.facturaPaciente;
   }

   @Override
   public void setFacturaPaciente(FacturaPaciente facturaCliente) {
      this.facturaPaciente = facturaCliente;
   }
} // fin de la clase ReciboImpl