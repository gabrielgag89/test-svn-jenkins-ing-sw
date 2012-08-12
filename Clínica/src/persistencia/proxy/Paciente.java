package persistencia.proxy;

public interface Paciente {
   public int getDni();
   
   public void setDni(int dni);
   
   public String getDomicilio();
   
   public void setDomicilio(String domicilio);
   
   public Plan getPlan();
   
   public void setPlan(Plan plan );
     
   public String getNombre();
   
   public void setNombre(String nombreP);
   
   public int getNumPaciente();
   
   public void setNumPaciente(int numPaciente);
   
   public String getTelefono();
   
   public void setTelefono(String telefono);
}//fin interface Paciente