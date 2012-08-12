package persistencia.proxy;

import java.util.Date;

public interface FacturaPaciente {
   public int getNumFactura();

   public void setNumFactura(int numFactura);

   public Date getFechaEmision();

   public void setFechaEmision(Date fechaEmision);

   public double getMonto();

   public void setMonto(double monto);

   public FichaInternacion getFichaInternacion();

   public void setFichaInternacion(FichaInternacion fichaInternacion);

   public EstadoFacturaPaciente getEstadoFacturaPaciente();

   public void setEstadoFacturaPaciente(EstadoFacturaPaciente estadoFacturaPaciente);
} // fin de la clase FacturaPaciente