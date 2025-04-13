package Graficas;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class PanelTexto {
    public void MostrarInfo(JTextPane panel, JTextField monto, JTextField razon, JDateChooser fecha){
        String egreso = monto.getText();
        String motivo = razon.getText();
        
        SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
        
        String momento = ff.format(fecha.getCalendar().getTime());
        
        if(panel == null){
            panel.setText("Monto: "+egreso+"\n"+"Razon: "+motivo+"\n"+"Fecha: "+momento+"\n\n");
        }
        else{
            String cadena = panel.getText();
            panel.setText(cadena + "Monto: "+egreso+"\n"+"Razon: "+motivo+"\n"+"Fecha: "+momento+"\n\n");
        }
    }
}
