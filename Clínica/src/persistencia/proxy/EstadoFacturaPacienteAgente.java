package persistencia.proxy;

public class EstadoFacturaPacienteAgente extends ObjetoPersistente implements EstadoFacturaPaciente {
   private EstadoFacturaPacienteImpl impl;

   public void setImplementacion(EstadoFacturaPacienteImpl impl){
      this.impl = impl;
   }
   
   @Override
   public String getNombreEstado() {
      return this.impl.getNombreEstado();
   }

   @Override
   public void setNombreEstado(String nombreEstado) {
      this.impl.setNombreEstado(nombreEstado);
   }
} // fin de la clase EstadoFacturaPacienteAgente