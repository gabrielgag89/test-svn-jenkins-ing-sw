package persistencia.proxy;

public class PacienteImpl implements Paciente {
   private int numeroPaciente;
   private String nombrePaciente;
   private int dni;
   private String domicilio;
   private String telefono;
   private Plan plan;

   @Override
   public int getDni() {
      return this.dni;
   }

   @Override
   public void setDni(int dni) {
      this.dni = dni;
   }

   @Override
   public String getDomicilio() {
      return this.domicilio;
   }

   @Override
   public void setDomicilio(String domicilio) {
      this.domicilio = domicilio;
   }

   @Override
   public Plan getPlan() {
      return this.plan;
   }

   @Override
   public void setPlan(Plan plan) {
      this.plan = plan;
   }

   @Override
   public String getNombre() {
      return this.nombrePaciente;
      }

   @Override
   public void setNombre(String nombreP) {
      this.nombrePaciente = nombreP;
   }

   @Override
   public int getNumPaciente() {
      return this.numeroPaciente;
   }

   @Override
   public void setNumPaciente(int numPaciente) {
      this.numeroPaciente = numPaciente;
   }

   @Override
   public String getTelefono() {
      return this.telefono;
   }

   @Override
   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }
} // fin de la clase PacienteImpl