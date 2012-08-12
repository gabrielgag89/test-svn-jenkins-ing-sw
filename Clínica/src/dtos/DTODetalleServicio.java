package dtos;

public class DTODetalleServicio {
   private String nombreServicio;
   private int cantidad;
   private double monto;
   private double subtotal;

   public String getNombreServicio() {
      return nombreServicio;
   }

   public void setNombreServicio(String nombreServicio) {
      this.nombreServicio = nombreServicio;
   }

   public int getCantidad() {
      return cantidad;
   }

   public void setCantidad(int cantidad) {
      this.cantidad = cantidad;
   }

   public double getMonto() {
      return monto;
   }

   public void setMonto(double monto) {
      this.monto = monto;
   }

   public double getSubtotal() {
      return subtotal;
   }

   public void setSubtotal(double subtotal) {
      this.subtotal = subtotal;
   }
} // fin de la clase DTODetalleServicio