package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MovimientoFinanciero extends Gastos {
    private String tipo;

    public MovimientoFinanciero() {
    }

    public MovimientoFinanciero(String NombreUsuario, String fecha, String tipo) {
        super(NombreUsuario, fecha);
        this.tipo = tipo;
    }

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void agregarInfo(String razon, double monto) {
        super.transaccion.put(razon, super.transaccion.getOrDefault(razon, 0.0) + monto);
        Clases.CManejoArchivos objeto = new Clases.CManejoArchivos(this);
        objeto.registrarInfoUsuario(razon, monto);
    }
    
    public void cargarIngresosDesdeArchivo() {
            super.transaccion.clear(); 
            try (BufferedReader br = new BufferedReader(new FileReader("BaseDeDatos\\"+super.NombreUsuario+"_datos.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split("\\|");
                       if(partes.length == 4 && partes[3].equals(tipo)){
                           try{
                                String razon = partes[1];
                                double monto = Double.parseDouble(partes[2]);
                                super.transaccion.put(razon, super.transaccion.getOrDefault(razon, 0.0) + monto); 
                           } catch(NumberFormatException e){
                               System.out.println("Error al convertir monto en línea: " + linea);
                           }
                       }   
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer el archivo");
            }
          }
    
    public void cargarEgresosMes(int mes) {
        super.transaccion.clear(); 
        try (BufferedReader br = new BufferedReader(new FileReader("BaseDeDatos\\"+super.NombreUsuario+"_datos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                    if(partes.length == 4 && partes[3].equals(tipo)){
                        try{
                            String fechaStr = partes[0];
                            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                            Date fecha = formato.parse(fechaStr);
                            Calendar cal =Calendar.getInstance();
                            cal.setTime(fecha);
                            int mesArchivo = cal.get(Calendar.MONTH);
                                    
                            if(mes == mesArchivo){
                                try{
                                    String razon = partes[1];
                                    double monto = Double.parseDouble(partes[2]);
                                    super.transaccion.put(razon, super.transaccion.getOrDefault(razon, 0.0) + monto); 
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
    }
}
