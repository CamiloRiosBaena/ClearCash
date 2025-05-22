package Clases;

import Vista.FormLogin;
import Vista.formMenuPrincipal;
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

public class CManejoArchivos{
    
    private static final String rutaArchivo = "BaseDeDatos\\usuarios.txt";
    private MovimientoFinanciero movimiento;
    
    public CManejoArchivos() {
    }

    public CManejoArchivos(MovimientoFinanciero movimiento) {
        this.movimiento = movimiento;
    }

    public MovimientoFinanciero getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoFinanciero movimiento) {
        this.movimiento = movimiento;
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
        try (PrintWriter escritor = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            escritor.println(usuario + "," + contraseña);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public void validarUsuarioExistente(JPasswordField contra, JFrame ventanaActual){
        //Bandera para validar existencia de usuario 
        boolean existe = false;
        //Extraccion de los datos de los JTextField
        String dato1 = movimiento.getNombreUsuario();
        String dato2 = String.valueOf(contra.getPassword());
        
        try (BufferedReader entrada = new BufferedReader(new FileReader(rutaArchivo))) {
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
            JOptionPane.showMessageDialog(null, "Usuario existente","Error",JOptionPane.ERROR_MESSAGE);
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
        
        String dato1 = movimiento.getNombreUsuario();
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
            formMenuPrincipal objetoMenu = new formMenuPrincipal(dato1);
            objetoMenu.setVisible(true);
            ventanaActual.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Informacion invalida\n\nRevisela y vuelva a intentar");
        }
    }
    
    public void registrarInfoUsuario(List<MovimientoFinanciero> aux) {
        List<String> lineas = new ArrayList<>();
        boolean[] encontrados = new boolean[aux.size()]; 
        int idMaximo = 0;

        String archivo = "BaseDeDatos\\" + movimiento.getNombreUsuario() + "_datos.txt";

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length < 5) {
                    continue;
                }

                int idActual = Integer.parseInt(partes[0]);
                if (idActual > idMaximo) {
                    idMaximo = idActual;
                }

                boolean lineaModificada = false;
                for (int i = 0; i < aux.size(); i++) {
                    MovimientoFinanciero mov = aux.get(i);

                    if (partes[1].equals(mov.getFecha()) &&
                        partes[2].equals(mov.getRazon()) &&
                        partes[4].equals(mov.getTipo())) {

                        double montoExistente = Double.parseDouble(partes[3]);
                        double montoTotal = montoExistente + mov.getMonto();
                        String nuevaLinea = (idActual) + "|" + mov.getFecha() + "|" + mov.getRazon() + "|" + montoTotal + "|" + mov.getTipo();
                        lineas.add(nuevaLinea);
                        encontrados[i] = true;
                        lineaModificada = true;
                        break;
                    }
                }

                if (!lineaModificada) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        for (int i = 0; i < aux.size(); i++) {
            if (!encontrados[i]) {
                MovimientoFinanciero mov = aux.get(i);
                idMaximo++;
                String nuevaLinea = idMaximo + "|" + mov.getFecha() + "|" + mov.getRazon() + "|" + mov.getMonto() + "|" + mov.getTipo();
                lineas.add(nuevaLinea);
            }
        }

        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
            for (String l : lineas) {
                escritor.println(l);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public void guardarMovimientosEnArchivo(List<MovimientoFinanciero> transacciones) {
        String archivo = "BaseDeDatos\\" + movimiento.getNombreUsuario() + "_datos.txt"; 

        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
            for (Clases.MovimientoFinanciero mov : transacciones) {
                String linea = mov.getID() + "|" + mov.getFecha() + "|" + mov.getRazon() + "|" + mov.getMonto() + "|" + mov.getTipo();
                escritor.println(linea);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los cambios en el archivo: " + e.getMessage());
        }
    }

}
