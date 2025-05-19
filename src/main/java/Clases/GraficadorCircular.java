package Clases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JPanel;

public class GraficadorCircular extends Clases.MovimientoFinanciero{
          
          public JPanel generarGrafico(List<MovimientoFinanciero> grafica) {
            DefaultPieDataset dataset = new DefaultPieDataset();

            Map<String, Double> mapaMontos = new HashMap<>();
            
            for (MovimientoFinanciero aux : grafica) {
                String razonTransaccion = aux.getRazon();
                double montoTransaccion = aux.getMonto();
                
                mapaMontos.put(razonTransaccion, mapaMontos.getOrDefault(razonTransaccion, 0.0) + montoTransaccion);
            }
            
            for (Map.Entry<String, Double> entry : mapaMontos.entrySet()) {
                dataset.setValue(entry.getKey(), entry.getValue());
            }

            JFreeChart chart = ChartFactory.createPieChart(
                "Distribución de " + super.getTipo(),
                dataset,
                true, true, true
            );

            return new ChartPanel(chart);
          }  
}
