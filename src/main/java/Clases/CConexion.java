package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConexion {
    Connection conectar;
    
    String usuario = "root";
    String contraseña = "Delfin24c*69";
    String BaseDeDatos = "Login";
    String ip = "127.0.0.1";//"192.168.1.10";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+BaseDeDatos;
    
    public Connection establecerConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            
            JOptionPane.showMessageDialog(null, "Conexión exitosa");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Problemas en la conexión" + e.toString());
        }
        return conectar;
    }
}
