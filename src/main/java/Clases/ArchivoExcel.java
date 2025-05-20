package Clases;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ArchivoExcel {
    public void crearArchivo(){
        String home = System.getProperty("user.home");
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Movimientos");

        // Crear estilo para encabezado
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        // Crear encabezados
        String[] columnas = {"Fecha", "Razón", "Monto", "Tipo"};
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columnas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnas[i]);
            cell.setCellStyle(headerStyle);
        }

        // Datos de ejemplo
        Object[][] datos = {
            {"2025-05-20", "Sueldo", 5000000, "Ingreso"},
            {"2025-05-22", "Alquiler", 800000, "Egreso"},
            {"2025-05-23", "Comida", 150000, "Egreso"}
        };

        // Agregar los datos
        int rowNum = 1;
        for (Object[] fila : datos) {
            Row row = sheet.createRow(rowNum++);
            for (int col = 0; col < fila.length; col++) {
                Cell cell = row.createCell(col);

                if (fila[col] instanceof String) {
                    cell.setCellValue((String) fila[col]);
                } else if (fila[col] instanceof Integer) {
                    cell.setCellValue((Integer) fila[col]);
                } else if (fila[col] instanceof Double) {
                    cell.setCellValue((Double) fila[col]);
                }
            }
        }

        // Autoajustar tamaño de columnas
        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar archivo
        try (FileOutputStream fileOut = new FileOutputStream(home+"\\Downloads\\movimientos.xlsx")) {
            workbook.write(fileOut);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

