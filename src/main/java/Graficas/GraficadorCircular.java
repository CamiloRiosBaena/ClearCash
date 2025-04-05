package Graficas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GraficadorCircular {
      
          private Map<String, Double> ingreso;
          private DefaultPieDataset dataset = new DefaultPieDataset();
          
          public GraficadorCircular(){
              ingreso = new HashMap<>();
          }
          
          public void agregarIngreso(String razon, double monto) {
               ingreso.put(razon, ingreso.getOrDefault(razon, 0.0) + monto);
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
