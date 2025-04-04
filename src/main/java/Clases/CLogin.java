package Clases;

import com.mycompany.clearcash.FormMainMenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CLogin {
   public void validarUsuario(JTextField usuario, JPasswordField contraseña, JFrame actualPanel){
       try{
           ResultSet rs = null;
           PreparedStatement ps = null;
           
           Clases.CConexion objetoConexion = new Clases.CConexion();
           
           String consulta = "select * from Users where Users.username = (?) and Users.typePassword = (?)";
           
           ps = objetoConexion.establecerConexion().prepareStatement(consulta);
           
           String contra = String.valueOf(contraseña.getPassword());
           
           ps.setString(1, usuario.getText());
           ps.setString(2, contra);
           
           rs = ps.executeQuery();
           
           if(rs.next()){
               JOptionPane.showMessageDialog(null, "Ingreso exitoso");
               
               FormMainMenu objetoMenu = new FormMainMenu();
               objetoMenu.setVisible(true);
               actualPanel.dispose();
           }
           else{
               JOptionPane.showMessageDialog(null, "Error al iniciar sesión");
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Erro: "+e);
       }
   } 
}
