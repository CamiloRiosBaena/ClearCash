package Vista;

public class ClearCash {

    public static void main(String[] args) {
        Clases.CManejoArchivos objetoArchivos = new Clases.CManejoArchivos();
        objetoArchivos.crearArchivo("BaseDeDatos\\usuarios.txt");
        
        Clases.ArchivoExcel objetoExcel = new Clases.ArchivoExcel();
        objetoExcel.crearArchivo();
        FormLogin objt = new FormLogin();
        objt.setVisible(true);
    }
}
