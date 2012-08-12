package persistencia.proxy;

import java.util.Date;
import java.util.List;

public interface FichaInternacion {
   public int getNroFicha();
   
   public void setNroFicha(int nroFicha);
   
   public Date getFechaCreacion();
   
   public void setFechaCreacion(Date fechaCreacion);
   
   public Prestacion getPrestacion();
   
   public void setPrestacion(Prestacion prestacion);
   
   public EstadoFichaInternacion getEstadoFichaInternacion();
   
   public void setEstadoFichaInternacion(EstadoFichaInternacion estadoFichaInternacion);
   
   public Cama getCama();
   
   public void setCama(Cama cama);
   
   public Paciente getPaciente();
   
   public void setPaciente(Paciente paciente);
   
   public List<DetalleFicha> getDetalleFicha();
   
   public void setDetalleFicha(List<DetalleFicha> ListaDetalleFicha);
   
   public void addDetalleFicha(DetalleFicha detalleFicha);
   
   public boolean deleteDetalleFicha(DetalleFicha detalleFicha);
}