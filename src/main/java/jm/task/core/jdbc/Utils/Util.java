package jm.task.core.jdbc.Utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String URL_KEY = "db.url";
    private static final String NAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    static Connection connection = null;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(NAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            System.out.println("<<<Util.getConnection>>> Ошибка подключения");
            e.printStackTrace();
        }
        return connection;
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("<<<Util>>> \"com.mysql.cj.jdbc.Driver\"" + e);
            throw new RuntimeException(e);
        }
    }
}
