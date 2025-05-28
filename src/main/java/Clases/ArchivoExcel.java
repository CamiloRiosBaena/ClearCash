package Clases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ArchivoExcel {
    
    private String home = System.getProperty("user.home");
    
    public void crearArchivo(List<MovimientoFinanciero> transacciones){
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Movimientos");

        // Crear estilo para encabezado
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        // Crear encabezados
        String[] columnas = {"ID","Fecha", "Razón", "Monto", "Tipo"};
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowNum = 1;
        for (MovimientoFinanciero aux : transacciones) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(aux.getID());
            row.createCell(1).setCellValue(aux.getFecha());
            row.createCell(2).setCellValue(aux.getRazon());
            row.createCell(3).setCellValue(aux.getMonto());
            row.createCell(4).setCellValue(aux.getTipo());
        }

        // Autoajustar tamaño de columnas
        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar archivo
        try (FileOutputStream fileOut = new FileOutputStream(home+"\\Downloads\\movimientos.xlsx")) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null,"Archivo creado exitosamente en: "+home+"\\Donwloads","Error",JOptionPane.INFORMATION_MESSAGE);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

