package Graficas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class GraficadorCircular {
      
          private Map<String, Double> ingreso;
          
          public GraficadorCircular(){
              ingreso = new HashMap<>();
          }
          
          public void cargarIngresosDesdeArchivo(String usuario) {
            ingreso.clear(); 
            try (BufferedReader br = new BufferedReader(new FileReader("BaseDeDatos\\"+usuario+"_datos.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split("\\|");
                       if(partes.length == 4 && partes[3].equals("ingreso")){
                           try{
                                String razon = partes[1];
                                double monto = Double.parseDouble(partes[2]);
                                ingreso.put(razon, ingreso.getOrDefault(razon, 0.0) + monto); 
                           } catch(NumberFormatException e){
                               System.out.println("Error al convertir monto en línea: " + linea);
                           }
                       }   
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer el archivo");
            }
          }

          
          public void agregarIngreso(String usuario, String razon, double monto, String fecha) {
               ingreso.put(razon, ingreso.getOrDefault(razon, 0.0) + monto);
               Clases.CManejoArchivos objeto = new Clases.CManejoArchivos();
               objeto.registrarInfoUsuario(usuario, razon, monto, fecha, "ingreso");
          }

          public JPanel generarGrafico() {
            DefaultPieDataset dataset = new DefaultPieDataset();

        
            for (Map.Entry<String, Double> entrada : ingreso.entrySet()) {
                dataset.setValue(entrada.getKey(), entrada.getValue());
            }

            JFreeChart chart = ChartFactory.createPieChart(
                "Distribución de Ingresos",
                dataset,
                true, true, true
            );

            return new ChartPanel(chart);
          }  
}
