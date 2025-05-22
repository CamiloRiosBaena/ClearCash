package Clases;

import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Transaccion {
    protected String NombreUsuario, fecha, razon;
    protected double monto;

    public Transaccion() {
    }

    public Transaccion(String NombreUsuario, String fecha, String razon, double monto) {
        this.NombreUsuario = NombreUsuario;
        this.fecha = fecha;
        this.razon = razon;
        this.monto = monto;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
