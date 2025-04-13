package com.mycompany.clearcash;

public class ClearCash {

    public static void main(String[] args) {
        Clases.CManejoArchivos objetoArchivos = new Clases.CManejoArchivos();
        objetoArchivos.crearArchivo("BaseDeDatos\\usuarios.txt");
        
        FormLogin objt = new FormLogin();
        objt.setVisible(true);
    }
}
