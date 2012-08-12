package persistencia.proxy;

import java.util.Date;

public class CostoServicioImpl implements CostoServicio {
    private Date fechaInicio;
    private Date fechaFin;
    private double monto;
    private ServicioEspecial servicioEspecial;

    @Override
    public Date getFechaInicio() {
        return fechaInicio;
    }

    @Override
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public Date getFechaFin() {
        return fechaFin;
    }

    @Override
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public double getMonto() {
        return monto;
    }

    @Override
    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public ServicioEspecial getServicioEspecial() {
        return servicioEspecial;
    }

    @Override
    public void setServicioEspecial(ServicioEspecial servicioEspecial) {
        this.servicioEspecial = servicioEspecial;
    }
}