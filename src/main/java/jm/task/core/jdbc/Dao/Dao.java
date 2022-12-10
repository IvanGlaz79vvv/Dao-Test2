package jm.task.core.jdbc.Dao;


import jm.task.core.jdbc.Model.User;
import jm.task.core.jdbc.Utils.Util;
import org.apache.commons.collections4.list.TreeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Dao {

    static String path = "C:\\Users\\wk\\Desktop\\Квартира.xlsx]";
    String passenger_no;
    String passenger_name;
    int flight_id;
    String seat_no;
    double cost;

    private static User user = new User();
    private static String line;
    private static StringBuilder content = new StringBuilder();
    BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/input.txt"));
    private static final Connection conn = Util.getConnection();
    private static final String CREATETABLE = "CREATE TABLE IF NOT EXISTS newtable"
            + "(id int PRIMARY KEY AUTO_INCREMENT, "
            + "passenger_no varchar(32),"
            + "passenger_name varchar(45), "
            + "flight_id int(45),"
            + "seat_no varchar(4),"
            + "cost varchar(32))";

    private static final String TRUNCATE = "TRUNCATE TABLE newtable";

    private static final String DROP = "DROP TABLE IF EXISTS newtable";
    private static final String SAVEUSER = "INSERT INTO newtable (passenger_no, passenger_name, flight_id, seat_no, cost) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM newtable WHERE id = ?";

    private static final String SELECTALL = "SELECT * FROM newtable";
    private static final String SELECTid = "SELECT * FROM newtable WHERE id = ?";
    private static final String SELECTpassenger_no = "SELECT * FROM newtable WHERE passenger_no = ?";
    private static final String SELECTpassenger_name = "SELECT * FROM newtable WHERE passenger_name = ?";
    private static final String SELECTposition = "SELECT * FROM newtable WHERE passenger_name = ?";

    public Dao() throws IOException {
    }
//    private static User user = null;


    public static void createTable() throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//        System.out.println("<<<createTable>>>: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        try (Statement statement = conn.createStatement()) {
            System.out.println(conn.getTransactionIsolation());

            conn.setAutoCommit(false);
            statement.executeUpdate(CREATETABLE);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                System.out.println("^^^^^<<<Dao>>> Сработал createTable -> rollback()^^^^^\n");
            } catch (SQLException ex) {
                System.out.println("\n-----<<<Dao>>> Начало ошибки rollback() createTable-----");
                ex.printStackTrace();
                System.out.println("^^^^^<<<Dao>>> Конец ошибки rollback() createTable^^^^^");
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("-----<<<Dao>>> Начало ошибки setAutoCommit(true) createTable-----");
                e.printStackTrace();
                System.out.println("^^^^^<<<Dao>>> Ошибка setAutoCommit(true) createTable^^^^^");
            }
        }
    }

    public static List<User> getAllUsers() throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        List<User> arrayListnewTable = new TreeList<>();
        conn.setAutoCommit(false);
        try (ResultSet resultSet = conn.createStatement().executeQuery(SELECTALL)) {

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                String passenger_no = resultSet.getString(2); // получили имя
                String passenger_name = resultSet.getString(3); // получили поле position
                int flight_id = resultSet.getInt(4); // получили date
                String seat_no = resultSet.getString(5);
                double cost = resultSet.getDouble(6);

                user = new User(userId, passenger_no, passenger_name, flight_id, seat_no, cost);

                arrayListnewTable.add(user);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("<<<getAllUsers>>> Запущен rollback()" + e);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return arrayListnewTable;
    }

    public static void saveUser
            (String passenger_no, String passenger_name,
             int flight_id, String seat_no, String cost)
            throws SQLException {

        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//        System.out.println("<<<Dao.saveUser>>>: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        conn.setAutoCommit(false);
        try (PreparedStatement pstmt = conn.prepareStatement(SAVEUSER)) {
            /*
             + "passenger_no varchar(32),"
            + "passenger_name varchar(45), "
            + "flight_id int(45),"
            + "seat_no varchar(4),"
            + "cost numeric(8, 2))";
            * */

            pstmt.setString(1, passenger_no);
            pstmt.setString(2, passenger_name);
            pstmt.setInt(3, flight_id);
            pstmt.setString(4, seat_no);
            pstmt.setString(5, cost);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            System.err.println("<<<saveUser>>> " + e);
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public static void addFromFile(String path) throws IOException {
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine()) {
            String name = scanner.next();
            String position = scanner.next();
            String date = scanner.next();

            System.out.println(name);
            System.out.println(position);
            System.out.println(date);
            System.out.println();
        }
    }

    public static User getUserById(int id) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//        System.out.println("<<<getUserById>>>: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECTid)) {

            conn.setAutoCommit(false);

            preparedStatement.setInt(1, id);  // так мы подставляем вместо знака вопроса нужный id
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                String passenger_no = resultSet.getString(2); // получили имя
                String passenger_name = resultSet.getString(3); // получили поле position
                int flight_id = resultSet.getInt(4); // получили date
                String seat_no = resultSet.getString(5);
                double cost = resultSet.getDouble(6);

                user = new User(userId, passenger_no, passenger_name, flight_id, seat_no, cost);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("<<<getUserById>>> Запущен rollback()" + e);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return user;
    }

    public static List<User> getUserByPassenger_no(String passenger_no) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//        System.out.println("<<<getUserByName>>>: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        List<User> arrayUsersByName = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECTpassenger_no)) {  /*"SELECT * FROM newtable WHERE name = ? LIMIT 1"*/
            conn.setAutoCommit(false);

            preparedStatement.setString(1, passenger_no);  // так мы подставляем вместо знака вопроса нужный passenger_no
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                passenger_no = resultSet.getString(2); // получили имя
                String passenger_name = resultSet.getString(3); // получили поле position
                int flight_id = resultSet.getInt(4); // получили date
                String seat_no = resultSet.getString(5);
                double cost = resultSet.getDouble(6);

                user = new User(userId, passenger_no, passenger_name, flight_id, seat_no, cost);

                arrayUsersByName.add(user);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("<<<getUserByName>>> Запущен rollback()" + e);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return arrayUsersByName;
    }

    public static List<User> getUserByName(String passenger_name) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("<<<getUserByPosition>>>: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        List<User> arrayUsersByPosition = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECTposition)) {  /*"SELECT * FROM newtable WHERE position = ?*/
            conn.setAutoCommit(false);

            preparedStatement.setString(1, passenger_name);  // так мы подставляем вместо знака вопроса нужный passenger_name
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                String passenger_no = resultSet.getString(2); // получили имя
                passenger_name = resultSet.getString(3); // получили поле position
                int flight_id = resultSet.getInt(4); // получили date
                String seat_no = resultSet.getString(5);
                double cost = resultSet.getDouble(6);

                user = new User(userId, passenger_no, passenger_name, flight_id, seat_no, cost); //собираем юзера

                arrayUsersByPosition.add(user);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("<<<getUserByName>>> Запущен rollback()" + e);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return arrayUsersByPosition;
    }

    public void cleanUsersTable() throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("<<<cleanUsersTable>>>: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        try (Statement statement = conn.createStatement()) {
            conn.setAutoCommit(false);
            statement.executeUpdate(TRUNCATE);
            System.out.println("\nВсе пользователи удалены\n");
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            System.err.println("<<<cleanUsersTablee>>> rollback() " + e);
        }
    }

    public void dropUsersTable() throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        try (Statement statement = conn.createStatement()) {
            conn.setAutoCommit(false);
            statement.executeUpdate(DROP);
            System.out.println("Таблица удалена");
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("<<<dropUsersTable>>> Ошибка rollback() " + e);
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public static int getLastdId() throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//        System.out.println("<<<Dao.getLastdId>>>: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        int userId;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT MAX(id) FROM newtable")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userId = resultSet.getInt(1); // получили id пользователя
        }
        return userId;
    }

    public static List<User> getUserBypassenger_no(String passenger_no) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        List<User> arrayUsersByFlight_id = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECTpassenger_no)) {  /*"SELECT * FROM newtable WHERE date = ? LIMIT 1"*/
            conn.setAutoCommit(false);

            preparedStatement.setString(1, passenger_no);  // так мы подставляем вместо знака вопроса нужный id
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                passenger_no = resultSet.getString(2); // получили имя
                String passenger_name = resultSet.getString(3); // получили поле position
                int flight_id = resultSet.getInt(4); // получили date
                String seat_no = resultSet.getString(5);
                double cost = resultSet.getDouble(6);

                user = new User(userId, passenger_no, passenger_name, flight_id, seat_no, cost);
                arrayUsersByFlight_id.add(user);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("<<<getUserById>>> Запущен rollback()" + e);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return arrayUsersByFlight_id;
    }

    public static void removeUserById(int id) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        conn.setAutoCommit(false);
        try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.err.println("<<<removeUserById>>> Запущен rollback()" + e);
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

}