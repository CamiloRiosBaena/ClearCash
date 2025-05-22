package Clases;

import Vista.FormLogin;

public class ClearCash {

    public static void main(String[] args) {
        CManejoArchivos objetoArchivos = new Clases.CManejoArchivos();
        objetoArchivos.crearArchivo("BaseDeDatos\\usuarios.txt");
        
        FormLogin objt = new FormLogin();
        objt.setVisible(true);
    }
}
