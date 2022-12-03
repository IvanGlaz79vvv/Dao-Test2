package jm.task.core.jdbc.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class InClass {
    private static String name = null;
    private static String position = null;
    private static String date = null;

    private static int i = 0;

    static List<String> arrayCells = new ArrayList<>();
    private static String inString;
    private static int inInt;
    private static String text;

    static String path = "C:\\Users\\wk\\Desktop\\Квартира.xlsx";

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
    public static void readFromExcel() throws IOException {
//        Map<Integer,Object> data = new LinkedHashMap();
//        try (FileInputStream inputStream = new FileInputStream(path)) {
        List data = new ArrayList();
        Workbook workbook = new XSSFWorkbook(path); //(InClass.path);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            System.out.println("");
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING -> {
                        data.add(cell.getRichStringCellValue().getString());
                        System.out.println(" " + (cell.getAddress()) + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    case _NONE -> {
                        System.out.println(" " + cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    case NUMERIC -> {
                        System.out.println(" " + (cell.getAddress()) + " - " + (cell.getCellType()) + " : " + cell);
                        if (DateUtil.isCellDateFormatted(cell)) {
                            data.add(cell.getDateCellValue());
                        } else {
                            data.add(cell.getNumericCellValue());
                        }
                    }
                    case BLANK -> {
//                        System.out.println(" " + cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    case BOOLEAN -> {
                        System.out.println(" " + cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                        data.add(cell.getBooleanCellValue());
                    }
                    case FORMULA -> {
                        System.out.println(" " + cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                        data.add(cell.getCellFormula());
                    }
                    case ERROR -> {
                        System.out.println(" " + cell.getAddress() + " - " + (cell.getCellType()) + " : " + cell);
                    }
                    default -> data.add(" ");
                }
            }
        }
        System.out.println(data.toString());
    }
}
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>