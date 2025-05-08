package Clases;

import com.mycompany.clearcash.FormLogin;
import com.mycompany.clearcash.FormMainMenu;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CManejoArchivos {
    public static void crearArchivo(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        
        try {
            archivo.createNewFile();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public static void registrarUsuario(String usuario, String contraseña) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter("BaseDeDatos\\usuarios.txt", true))) {
            escritor.println(usuario + "," + contraseña);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public static void validarUsuarioExistente(JTextField usuario, JPasswordField contra, JFrame ventanaActual){
        boolean existe = false;
        String dato1 = usuario.getText();
        String dato2 = String.valueOf(contra.getPassword());
        
        try (BufferedReader entrada = new BufferedReader(new FileReader("BaseDeDatos\\usuarios.txt"))) {
            String linea;
            while((linea = entrada.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if(datos.length == 2 && datos[0].equals(dato1)){
                    existe = true;
                    break;
                }
            }
        } catch (IOException e){
            System.out.println("Error al leer el archivo");
        }
        
        if (existe) {
            JOptionPane.showMessageDialog(null, "Usuario existente");
        }
        else {
            registrarUsuario(dato1, dato2);
            crearArchivo("BaseDeDatos\\"+ dato1 + "_datos.txt");
            
            FormLogin objetoLogin = new FormLogin();
            objetoLogin.setVisible(true);
            ventanaActual.dispose();
        }
    }
    
   public static void registrarInfoUsuario(String usuario, String razon, double monto, String fecha, String tipo) {
    String rutaArchivo = "BaseDeDatos\\" + usuario + "_datos.txt";
    List<String> lineas = new ArrayList<String>();
    boolean encontrado = false;

    try {
        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 4 && partes[0].equals(fecha) && partes[1].equals(razon) && partes[3].equals(tipo)) {
                    double montoExistente = Double.parseDouble(partes[2]);
                    double montoTotal = montoExistente + monto;
                    String nuevaLinea = fecha + "|" + razon + "|" + montoTotal + "|" + tipo;
                    lineas.add(nuevaLinea);
                    encontrado = true;
                } else {
                    lineas.add(linea);
                }
            }
            lector.close();
        }

        if (!encontrado) {
            lineas.add(fecha + "|" + razon + "|" + monto + "|" + tipo);
        }

        PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo));
        for (String l : lineas) {
            escritor.println(l);
        }
        escritor.close();

    } catch (IOException e) {
        System.out.println("Error al procesar el archivo: " + e.getMessage());
    }
}

       
    
    public static void ingreso(JTextField usuario, JPasswordField contra, JFrame ventanaActual){
        boolean correcto = false;
        
        String dato1 = usuario.getText();
        String dato2 = String.valueOf(contra.getPassword());
        
        try (BufferedReader entrada = new BufferedReader(new FileReader("BaseDeDatos\\usuarios.txt"))) {
            String linea;
            while((linea = entrada.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if(datos.length == 2 && datos[0].equals(dato1) && datos[1].equals(dato2)){
                    correcto = true;  
                }
            }
        } catch (IOException e){
            System.out.println("Error al leer el archivo");
        }
        
        if (correcto){
            FormMainMenu objetoMenu = new FormMainMenu();
            objetoMenu.setDato(dato1);
            objetoMenu.setVisible(true);
            ventanaActual.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Informacion invalida\n\nRevisela y vuelva a intentar");
        }
    }
}
