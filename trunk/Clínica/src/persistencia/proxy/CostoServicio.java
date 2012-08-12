package persistencia.proxy;

import java.util.Date;

public interface CostoServicio {
    
   public Date getFechaInicio();

   public void setFechaInicio(Date fechaInicio);

   public Date getFechaFin();

   public void setFechaFin(Date fechaFin);
   
   public double getMonto();

   public void setMonto(double monto); 
   
   public ServicioEspecial getServicioEspecial();

   public void setServicioEspecial (ServicioEspecial servicioEspecial);
   
}
