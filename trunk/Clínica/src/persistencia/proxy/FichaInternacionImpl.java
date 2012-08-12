package persistencia.proxy;

import java.util.Date;
import java.util.List;

public class FichaInternacionImpl implements FichaInternacion {
   private int nroFicha;
   private Date fechaCreacion;
   private Prestacion prestacion;
   private EstadoFichaInternacion estadoFichaInternacion;
   private Cama cama;
   private Paciente paciente;
   private List<DetalleFicha> detalleFicha;

   @Override
   public int getNroFicha() {
      return nroFicha;
   }

   @Override
   public void setNroFicha(int nroFicha) {
      this.nroFicha = nroFicha;
   }

   @Override
   public Date getFechaCreacion() {
      return fechaCreacion;
   }

   @Override
   public void setFechaCreacion(Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Override
   public Prestacion getPrestacion() {
      return prestacion;
   }

   @Override
   public void setPrestacion(Prestacion prestacion) {
      this.prestacion = prestacion;
   }

   @Override
   public EstadoFichaInternacion getEstadoFichaInternacion() {
      return estadoFichaInternacion;
   }

   @Override
   public void setEstadoFichaInternacion(EstadoFichaInternacion estadoFichaInternacion) {
      this.estadoFichaInternacion = estadoFichaInternacion;
   }

   @Override
   public Cama getCama() {
      return cama;
   }

   @Override
   public void setCama(Cama cama) {
      this.cama = cama;
   }

   @Override
   public Paciente getPaciente() {
      return paciente;
   }

   @Override
   public void setPaciente(Paciente paciente) {
      this.paciente = paciente;
   }

   @Override
   public List<DetalleFicha> getDetalleFicha() {
      return detalleFicha;
   }

   @Override
   public void setDetalleFicha(List<DetalleFicha> ListaDetalleFicha) {
      this.detalleFicha = ListaDetalleFicha;
   }

   @Override
   public void addDetalleFicha(DetalleFicha detalleFicha) {
      this.detalleFicha.add(detalleFicha);
   }

   @Override
   public boolean deleteDetalleFicha(DetalleFicha detalleFicha) {
      return this.detalleFicha.remove(detalleFicha);
   }
} // fin de la clase FichaInternacionImpl