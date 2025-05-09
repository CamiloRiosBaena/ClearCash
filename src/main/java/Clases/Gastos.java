package Clases;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Gastos {
    protected String NombreUsuario, fecha;
    protected Map<String, Double> transaccion;

    public Gastos() {
        this.transaccion = new HashMap<>();
    }

    public Gastos(String NombreUsuario, String fecha) {
        this.NombreUsuario = NombreUsuario;
        this.fecha = fecha;
        this.transaccion = new HashMap<>();
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

    public Map<String, Double> getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Map<String, Double> transaccion) {
        this.transaccion = transaccion;
    }
    
    public void MostrarInfo(JTextPane panel, JTextField monto, JTextField razon){
        String egreso = monto.getText();
        String motivo = razon.getText();
        
        if(panel == null){
            panel.setText("Monto: "+egreso+"\n"+"Razon: "+motivo+"\n"+"Fecha: "+fecha+"\n\n");
        }
        else{
            String cadena = panel.getText();
            panel.setText(cadena + "Monto: "+egreso+"\n"+"Razon: "+motivo+"\n"+"Fecha: "+fecha+"\n\n");
        }
    }
}
