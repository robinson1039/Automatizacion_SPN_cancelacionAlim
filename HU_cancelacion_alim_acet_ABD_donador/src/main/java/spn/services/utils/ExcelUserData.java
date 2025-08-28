package spn.services.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUserData {
    /**
     * Lee datos de un archivo Excel y devuelve una lista de mapas.
     * Cada mapa representa una fila con las claves como encabezados.
     *
     * @param rutaArchivoExcel Ruta del archivo Excel.
     * @param hojaNombre       Nombre de la hoja donde están los datos.
     * @return Lista de mapas con los datos.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static List<Map<String, String>> leerDatos(String rutaArchivoExcel, String hojaNombre) throws IOException {
        List<Map<String, String>> datos = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(rutaArchivoExcel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Obtener la hoja de trabajo
            Sheet sheet = workbook.getSheet(hojaNombre);
            if (sheet == null) {
                throw new IllegalArgumentException("La hoja " + hojaNombre + " no existe en el archivo.");
            }

            // Leer encabezados (primera fila)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IllegalArgumentException("La hoja no tiene encabezados.");
            }

            // Guardar los encabezados en una lista
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }

            // Leer las filas de datos
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row dataRow = sheet.getRow(i);
                if (dataRow == null) continue;

                Map<String, String> fila = new HashMap<>();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = dataRow.getCell(j);
                    String valor = obtenerValorDeCelda(cell);
                    fila.put(headers.get(j), valor);
                }
                datos.add(fila);
            }
        }

        return datos;
    }

    /**
     * Obtiene el valor de una celda como texto.
     *
     * @param cell Celda de Excel.
     * @return Valor de la celda como String.
     */
    private static String obtenerValorDeCelda(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
