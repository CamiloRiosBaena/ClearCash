package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MovimientoFinanciero extends Transaccion {
    private String tipo;
    private int ID;

    public MovimientoFinanciero() {
    }

    public MovimientoFinanciero(String NombreUsuario, String fecha, String razon, double monto, String tipo, int ID) {
        super(NombreUsuario, fecha, razon, monto);
        this.tipo = tipo;
        this.ID = ID;
    }

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void MostrarInfo(JTextPane panel){
        if(panel == null){
            panel.setText("Monto: "+super.monto+"\n"+"Razon: "+super.razon+"\n"+"Fecha: "+super.getFecha()+"\n"+tipo+"\n\n");
        }
        else{
            String cadena = panel.getText();
            panel.setText(cadena + "Monto: "+super.monto+"\n"+"Razon: "+super.razon+"\n"+"Fecha: "+super.getFecha()+"\n"+tipo+"\n\n");
        }
    }
      
    public List<MovimientoFinanciero> cargarInfoDesdeArchivoMes(int mes) {
        List<MovimientoFinanciero> transacciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("BaseDeDatos\\"+super.NombreUsuario+"_datos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                    if(partes.length == 5){
                        try{
                            String fechaStr = partes[1];
                            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                            Date fecha = formato.parse(fechaStr);
                            Calendar cal =Calendar.getInstance();
                            cal.setTime(fecha);
                            int mesArchivo = cal.get(Calendar.MONTH) + 1;
                            if(mes == mesArchivo){
                                try{
                                    String usuario = super.NombreUsuario;
                                    String razon = partes[2];
                                    double monto = Double.parseDouble(partes[3]);
                                    String tipoTransaccion = partes[4];
                                    int idMovimiento = Integer.parseInt(partes[0]);
                                    
                                    transacciones.add(new MovimientoFinanciero(usuario,fechaStr,razon,monto,tipoTransaccion, idMovimiento));
                                } catch(NumberFormatException e){
                                    System.out.println("Error al convertir monto en línea: " + linea);
                                }
                            }
                           }catch(Exception e){
                                System.out.println("Error al convertir la fecha: " + e.getMessage());
                           }
                       } 
            }
        } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer el archivo");
        }
        
        return transacciones;
    }
}
