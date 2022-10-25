package jm.task.core.jdbc.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class InClass {
    private static String inString;
    private static int inInt;

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
}

