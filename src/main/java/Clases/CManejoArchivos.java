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

public class CManejoArchivos extends MovimientoFinanciero{

    public CManejoArchivos() {
    }

    public CManejoArchivos(MovimientoFinanciero base) {
        this.setNombreUsuario(base.getNombreUsuario());
        this.setFecha(base.getFecha());
        this.transaccion = base.getTransaccion();
        this.setTipo(base.getTipo());
    }
    
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
    
    public void validarUsuarioExistente(JPasswordField contra, JFrame ventanaActual){
        //Bandera para validar existencia de usuario 
        boolean existe = false;
        //Extraccion de los datos de los JTextField
        String dato1 = super.getNombreUsuario();
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
        
        //Comprobacion de existencia 
        if (existe) {
            JOptionPane.showMessageDialog(null, "Usuario existente");
        }
        //Registro en caso de no existencia
        else {
            registrarUsuario(dato1, dato2);
            crearArchivo("BaseDeDatos\\"+ dato1 + "_datos.txt");
            
            FormLogin objetoLogin = new FormLogin();
            objetoLogin.setVisible(true);
            ventanaActual.dispose();
        }
    }
    
    public void ingreso(JPasswordField contra, JFrame ventanaActual){
        //Bandera para validar información
        boolean correcto = false;
        
        String dato1 = super.getNombreUsuario();
        String dato2 = String.valueOf(contra.getPassword());
        
        try (BufferedReader entrada = new BufferedReader(new FileReader("BaseDeDatos\\usuarios.txt"))) {
            String linea;
            while((linea = entrada.readLine()) != null) {
                String[] datos = linea.split(",");
                
                //Verificacion de la estructura cargada del archivo
                if(datos.length == 2 && datos[0].equals(dato1) && datos[1].equals(dato2)){
                    correcto = true;  
                }
            }
        } catch (IOException e){
            System.out.println("Error al leer el archivo");
        }
        
        //Condicional correspondiente a la validacion de la información
        if (correcto){
            FormMainMenu objetoMenu = new FormMainMenu(dato1);
            objetoMenu.setVisible(true);
            ventanaActual.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Informacion invalida\n\nRevisela y vuelva a intentar");
        }
    }
    
    public void registrarInfoUsuario(String razon, double monto) {
    //Se declara un ArrayList para almacenar la informacion existente en el archivo
    List<String> lineas = new ArrayList<String>();
    
    //Bandera para verificar informacion previa
    boolean encontrado = false;

        try (BufferedReader lector = new BufferedReader(new FileReader("BaseDeDatos\\"+super.getNombreUsuario()+"_datos.txt"))) {
                String linea;

                while ((linea = lector.readLine()) != null) {
                    String[] partes = linea.split("\\|");
                    //Condicional para verificar la estructura de la información
                    if (partes.length == 4 && partes[0].equals(super.getFecha()) && partes[1].equals(razon) && partes[3].equals(super.getTipo())) {
                        double montoExistente = Double.parseDouble(partes[2]);
                        double montoTotal = montoExistente + monto;
                        String nuevaLinea = super.getFecha() + "|" + razon + "|" + montoTotal + "|" + super.getTipo();
                        lineas.add(nuevaLinea);
                        encontrado = true;
                    } else {
                        lineas.add(linea);
                    }
                }
                lector.close();

            //Verificacion de exitencia de informacion coincidente 
            if (!encontrado) {
                lineas.add(super.getFecha() + "|" + razon + "|" + monto + "|" + super.getTipo());
            }

            PrintWriter escritor = new PrintWriter(new FileWriter("BaseDeDatos\\"+super.getNombreUsuario()+"_datos.txt"));

            //Se declara un for-each para escribir cada elemento del ArrayList en el archivo 
            for (String l : lineas) {
                escritor.println(l);
            }
            escritor.close();

        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
 
}
