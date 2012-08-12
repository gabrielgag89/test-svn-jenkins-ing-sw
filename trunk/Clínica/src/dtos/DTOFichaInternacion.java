package dtos;

import java.util.Date;
import java.util.List;

public class DTOFichaInternacion {
   private int nroFicha;
   private int numPaciente;
   private String nombrePaciente;
   private String nombrePrestacion;
   private double costoPrestacion;
   private double descuento;
   private Date fecha;
   private List<DTODetalleServicio> dtoDetalle;

   public int getNroFicha() {
      return nroFicha;
   }

   public void setNroFicha(int nroFicha) {
      this.nroFicha = nroFicha;
   }

   public int getNumPaciente() {
      return numPaciente;
   }

   public void setNumPaciente(int numPaciente) {
      this.numPaciente = numPaciente;
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

   public Date getFecha() {
      return fecha;
   }

   public void setFecha(Date fecha) {
      this.fecha = fecha;
   }

   public List<DTODetalleServicio> getDtoDetalle() {
      return dtoDetalle;
   }

   public void setDtoDetalle(List<DTODetalleServicio> dtoDetalle) {
      this.dtoDetalle = dtoDetalle;
   }
} // fin de la clase DTOFichaInternacion