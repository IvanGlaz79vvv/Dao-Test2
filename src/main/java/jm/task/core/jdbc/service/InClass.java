package jm.task.core.jdbc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class InClass {
    private static int i = 0;

    private static String name = null;
    private static String position = null;
    private static String date = null;

    static List<String> arrayCells = new ArrayList<>();
    private static String inString;
    private static int inInt;
    private static String text;

    static String path = "C:\\Users\\wk\\IdeaProjects\\Dao-Test2\\resources\\ticket.xlsx";

    public static String inputString() throws IOException, SQLException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        inString = bufferedReader.readLine();
//        bufferedReader.close();
        return inString;
    }

    public static int inputInteger() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        inInt = Integer.parseInt(bufferedReader.readLine());
//        bufferedReader.close();//   .bufferedReader.close();
        return inInt;
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static Map readFromExcelXlsX() throws IOException {
        StringBuilder sb = new StringBuilder();

        Map<String, Object> dataXlsX = new LinkedHashMap();
//        try (FileInputStream inputStream = new FileInputStream(path)) {
//        List dataXlsX = new ArrayList();
        Workbook workbook = new XSSFWorkbook(path); //(InClass.path);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            System.out.println("");
            for (Cell cell : row) {

                switch (cell.getCellType()) {
                    case STRING -> {
                        dataXlsX.put(cell.getAddress().toString(), cell.getRichStringCellValue().getString());
                        System.out.println((cell.getAddress()) + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    case _NONE -> {
                        System.out.println(cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    case NUMERIC -> {
                        System.out.println((cell.getAddress()) + " - " + (cell.getCellType()) + " : " + cell);
                        if (DateUtil.isCellDateFormatted(cell)) {
                            dataXlsX.put(cell.getAddress().toString(), cell.getDateCellValue());
                        } else {
                            dataXlsX.put(cell.getAddress().toString(), cell.getNumericCellValue());
                        }
                    }
                    case BLANK -> {
//                        System.out.println(" " + cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    case BOOLEAN -> {
                        System.out.println(cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                        dataXlsX.put(cell.getAddress().toString(), cell.getBooleanCellValue());
                    }
                    case FORMULA -> {
                        System.out.println(cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                        dataXlsX.put(cell.getAddress().toString(), cell.getCellFormula());
                    }
                    case ERROR -> {
                        System.out.println(cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    default -> dataXlsX.put(cell.getAddress().toString(), " ");
                }
            }
        }
        return dataXlsX;
    }
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /*public static Map readFromExcelXls() throws IOException {
        StringBuilder sb = new StringBuilder();

        Map<String, Object> dataXls = new LinkedHashMap();
//        try (FileInputStream inputStream = new FileInputStream(path)) {
//        List dataXls = new ArrayList();
        Workbook workbook = new XSSFWorkbook(path); //(InClass.path);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            System.out.println("");
            for (Cell cell : row) {

                switch (cell.getCellType()) {
                    case STRING -> {
                        dataXls.put(cell.getAddress().toString(), cell.getRichStringCellValue().getString());
                        System.out.println((cell.getAddress()) + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    case _NONE -> {
                        System.out.println(cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    case NUMERIC -> {
                        System.out.println((cell.getAddress()) + " - " + (cell.getCellType()) + " : " + cell);
                        if (DateUtil.isCellDateFormatted(cell)) {
                            dataXls.put(cell.getAddress().toString(), cell.getDateCellValue());
                        } else {
                            dataXls.put(cell.getAddress().toString(), cell.getNumericCellValue());
                        }
                    }
                    case BLANK -> {
//                        System.out.println(" " + cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    case BOOLEAN -> {
                        System.out.println(cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                        dataXls.put(cell.getAddress().toString(), cell.getBooleanCellValue());
                    }
                    case FORMULA -> {
                        System.out.println(cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                        dataXls.put(cell.getAddress().toString(), cell.getCellFormula());
                    }
                    case ERROR -> {
                        System.out.println(cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    default -> dataXls.put(cell.getAddress().toString(), " ");
                }
            }
        }
        dataXls.remove("A1");
        dataXls.remove("B1");
        dataXls.remove("C1");
        return dataXls;
    }*/
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
