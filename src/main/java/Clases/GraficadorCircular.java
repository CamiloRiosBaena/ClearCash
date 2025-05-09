package Clases;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.util.Map;
import javax.swing.JPanel;

public class GraficadorCircular extends Clases.MovimientoFinanciero{
          
          public JPanel generarGrafico(Map<String, Double> datos) {
            DefaultPieDataset dataset = new DefaultPieDataset();

        
            for (Map.Entry<String, Double> entrada : datos.entrySet()) {
                dataset.setValue(entrada.getKey(), entrada.getValue());
            }

            JFreeChart chart = ChartFactory.createPieChart(
                "Distribución de " + super.getTipo(),
                dataset,
                true, true, true
            );

            return new ChartPanel(chart);
          }  
}
