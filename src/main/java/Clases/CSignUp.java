package Clases;

import com.mycompany.clearcash.FormLogin;
import com.mycompany.clearcash.FormMainMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CSignUp {
    public void RegistroUsuario(JTextField usuario, JPasswordField contraseña, JFrame ventanaActual){
        try{
            ResultSet rs = null;
            PreparedStatement ps_c = null;
            PreparedStatement ps_r = null;
            Connection conn = null;

            Clases.CConexion objetoConexion = new Clases.CConexion();
            conn = objetoConexion.establecerConexion();
            
            String consulta = "select * from Users where Users.username = (?)";
            
            ps_c = conn.prepareStatement(consulta);
            ps_c.setString(1, usuario.getText());
            rs = ps_c.executeQuery();
            
            if(rs.next()){
               JOptionPane.showMessageDialog(null, "Usuario ya existente");
            }
            else{
                String nuevoUsuario = "insert into Users(username, typePassword) values ((?), (?))";
                
                ps_r = conn.prepareStatement(nuevoUsuario);
                
                String contra = String.valueOf(contraseña.getPassword());
           
                ps_r.setString(1, usuario.getText());
                ps_r.setString(2, contra);

                int resultado = ps_r.executeUpdate();
                
                if(resultado > 0){
                    JOptionPane.showMessageDialog(null,"Registro exitoso");
                
                    FormLogin objetoLogin = new FormLogin();
                    objetoLogin.setVisible(true);
                    ventanaActual.dispose();
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"No se pudo registrar el usuario");
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro: "+e);
        }
    }
}
