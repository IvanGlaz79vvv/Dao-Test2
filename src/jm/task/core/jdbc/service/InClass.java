package jm.task.core.jdbc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InClass {
    private static String inString;
    private static int inInt;

    public static String inputString() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        inString = String.valueOf(bufferedReader.readLine());
        bufferedReader.close();
        return inString;
    }




    public static int inputInteger() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        inInt = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.close();
        return inInt;
    }
}
