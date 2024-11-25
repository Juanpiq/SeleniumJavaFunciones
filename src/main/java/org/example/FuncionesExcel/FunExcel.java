package org.example.FuncionesExcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FunExcel {
    private WebDriver driver;

    public FunExcel(WebDriver driver) {
        this.driver = driver;
    }

    // Método para obtener el número de filas en una hoja
    public int getRowCount(String filePath, String sheetName) throws IOException, InvalidFormatException {
        // Declaración de la variable para el número de filas
        int rowCount = 0;

        // Usando try-with-resources para manejar el cierre automático de los flujos
        try (FileInputStream file = new FileInputStream(new File(filePath));
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {  // Usando XSSFWorkbook para archivos .xlsx

            // Obtener la hoja por su nombre
            Sheet sheet = workbook.getSheet(sheetName);

            // Obtener el número de filas físicas
            rowCount = sheet.getPhysicalNumberOfRows();
        } catch (IOException e) {
            e.printStackTrace();  // Imprime el error si hay un problema de I/O
        }
        return rowCount;
    }

    // Método para obtener el número de columnas en una hoja
    public int getColumnCount(String filePath, String sheetName) throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
        workbook.close();
        file.close();
        return columnCount;
    }

    // Método para leer datos de una celda
    public String readData(String filePath, String sheetName, int rowNum, int colNum) throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum - 1); // Filas en Excel comienzan desde 1
        Cell cell = row.getCell(colNum - 1); // Las celdas también comienzan desde 1
        String cellValue = cell.toString();  // Devuelve el valor de la celda como String
        workbook.close();
        file.close();
        return cellValue;
    }

    // Método para escribir datos en una celda
    public void writeData(String filePath, String sheetName, int rowNum, int colNum, String data) throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum - 1); // Filas en Excel comienzan desde 1
        if (row == null) {
            row = sheet.createRow(rowNum - 1);
        }
        Cell cell = row.createCell(colNum - 1); // Las celdas comienzan desde 1
        cell.setCellValue(data);  // Escribe el dato en la celda
        file.close();

        // Guardar los cambios en el archivo
        FileOutputStream outFile = new FileOutputStream(new File(filePath));
        workbook.write(outFile);
        outFile.close();
        workbook.close();
    }
}
