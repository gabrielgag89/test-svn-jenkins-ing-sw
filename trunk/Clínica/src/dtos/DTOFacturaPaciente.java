package dtos;

import java.util.Date;
import java.util.List;

public class DTOFacturaPaciente {
   private int numFactura;
   private Date fecha;
   private int nroFicha;
   private String nombrePaciente;
   private String nombrePrestacion;
   private double costoPrestacion;
   private double descuento;
   private List<DTODetalleServicio> dtoDetalle;
   private double monto;

   public int getNumFactura() {
      return numFactura;
   }

   public void setNumFactura(int numFactura) {
      this.numFactura = numFactura;
   }

   public Date getFecha() {
      return fecha;
   }

   public void setFecha(Date fecha) {
      this.fecha = fecha;
   }

   public int getNroFicha() {
      return nroFicha;
   }

   public void setNroFicha(int nroFicha) {
      this.nroFicha = nroFicha;
   }

   public String getNombrePaciente() {
      return nombrePaciente;
   }

   public void setNombrePaciente(String nombrePaciente) {
      this.nombrePaciente = nombrePaciente;
   }

   public String getNombrePrestacion() {
      return nombrePrestacion;
   }

   public void setNombrePrestacion(String nombrePrestacion) {
      this.nombrePrestacion = nombrePrestacion;
   }

   public double getCostoPrestacion() {
      return costoPrestacion;
   }

   public void setCostoPrestacion(double costoPrestacion) {
      this.costoPrestacion = costoPrestacion;
   }

   public double getDescuento() {
      return descuento;
   }

   public void setDescuento(double descuento) {
      this.descuento = descuento;
   }

   public List<DTODetalleServicio> getDtoDetalle() {
      return dtoDetalle;
   }

   public void setDtoDetalle(List<DTODetalleServicio> dtoDetalle) {
      this.dtoDetalle = dtoDetalle;
   }

   public double getMonto() {
      return monto;
   }

   public void setMonto(double monto) {
      this.monto = monto;
   }
} // fin de la clase DTOFacturaPaciente