package persistencia.proxy;

public class EstadoCamaImpl implements EstadoCama{
   private String nombreEstado;

   @Override
   public String getNombreEstado() {
      return nombreEstado;
   }

   @Override
   public void setNombreEstado(String nombreEstado) {
      this.nombreEstado = nombreEstado;
   }
}//end EstadoCama