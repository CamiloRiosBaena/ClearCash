package Clases;

import java.time.LocalDate;

public class CGastos {
    private String usuario, razonIngreso, razonEgreso, tipoGasto;
    private double montoIngreso, montoEgreso, sladoActual;
    private LocalDate fechaIngreso, fechaEgreso;

    public CGastos() {
    }

    public CGastos(String usuario, String razonIngreso, String razonEgreso, String tipoGasto, double montoIngreso, double montoEgreso, double sladoActual, LocalDate fechaIngreso, LocalDate fechaEgreso) {
        this.usuario = usuario;
        this.razonIngreso = razonIngreso;
        this.razonEgreso = razonEgreso;
        this.tipoGasto = tipoGasto;
        this.montoIngreso = montoIngreso;
        this.montoEgreso = montoEgreso;
        this.sladoActual = sladoActual;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRazonIngreso() {
        return razonIngreso;
    }

    public void setRazonIngreso(String razonIngreso) {
        this.razonIngreso = razonIngreso;
    }

    public String getRazonEgreso() {
        return razonEgreso;
    }

    public void setRazonEgreso(String razonEgreso) {
        this.razonEgreso = razonEgreso;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public double getMontoIngreso() {
        return montoIngreso;
    }

    public void setMontoIngreso(double montoIngreso) {
        this.montoIngreso = montoIngreso;
    }

    public double getMontoEgreso() {
        return montoEgreso;
    }

    public void setMontoEgreso(double montoEgreso) {
        this.montoEgreso = montoEgreso;
    }

    public double getSladoActual() {
        return sladoActual;
    }

    public void setSladoActual(double sladoActual) {
        this.sladoActual = sladoActual;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

}
