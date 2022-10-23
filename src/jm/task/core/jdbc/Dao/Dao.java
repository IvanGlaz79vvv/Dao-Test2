package jm.task.core.jdbc.Dao;



import jm.task.core.jdbc.Model.User;
import jm.task.core.jdbc.Utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Dao {

    private static User user = new User();

    private static final Connection conn = Util.getConnection();

    private static final String CREATETABLE = "CREATE TABLE IF NOT EXISTS newtable"
            + "(id int PRIMARY KEY AUTO_INCREMENT, "
            + "name varchar(100),"
            + "position varchar(100), "
            + "date D(100))";

    private static final String TRUNCATE = "TRUNCATE TABLE newtable";

    private static final String DROP = "DROP TABLE IF EXISTS newtable";
    private static final String SAVEUSER = "INSERT INTO newtable (name, position, date) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM newtable WHERE id = ?";

    private static final String SELECTALL = "SELECT * FROM newtable";
    private static final String SELECTid = "SELECT * FROM newtable WHERE id = ?";
    private static final String SELECTname = "SELECT * FROM newtable WHERE name = ?";
    private static final String SELECTposition = "SELECT * FROM newtable WHERE position = ?";
    private static final String SELECTdate = "SELECT * FROM newtable WHERE date = ?";
//    private static User user = null;


    public static void createTable() throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        try (Statement statement = conn.createStatement()) {
            System.out.println(conn.getTransactionIsolation());

            conn.setAutoCommit(false);
            statement.executeUpdate(CREATETABLE);
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("^^^^^Сработал createUsersTable() -> rollback()^^^^^\n");
            } catch (SQLException ex) {
                System.out.println("\n-----Начало ошибки rollback() createUsersTable()-----");
                ex.printStackTrace();
                System.out.println("^^^^^Конец ошибки rollback() createUsersTable()^^^^^");
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("-----Начало ошибки setAutoCommit(true) createUsersTable()-----");
                e.printStackTrace();
                System.out.println("^^^^^Ошибка setAutoCommit(true) createUsersTable()^^^^^");
            }
        }
    }
    public static List<User> getAllUsers() throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        List<User> arrayListnewTable = new ArrayList<>();
        conn.setAutoCommit(false);
        try (ResultSet resultSet = conn.createStatement().executeQuery(SELECTALL)) {


            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                String name = resultSet.getString(2); // получили имя
                String position = resultSet.getString(3); // получили поле position
                String date = resultSet.getString(4); // получили date

                user = new User(userId, name, position, date);

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
    public static void saveUser(String name, String position, String date) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        try (PreparedStatement pstmt = conn.prepareStatement(SAVEUSER)) {
            pstmt.setString(1, name);
            pstmt.setString(2, position);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            System.err.println("<<<saveUser>>> " + e);
        } finally {
            conn.setAutoCommit(true);
        }
    }
    public static User getUserById(int id) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        System.out.println("conn = " + conn);
        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECTid)) {

            conn.setAutoCommit(false);


            preparedStatement.setInt(1, id);  // так мы подставляем вместо знака вопроса нужный id
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                String name = resultSet.getString(2); // получили имя
                String position = resultSet.getString(3); // получили поле position
                String date = resultSet.getString(4); // получили date

                user = new User(userId, name, position, date);
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
    public static List<User> getUserByName(String name) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        List<User> arrayUsersByName = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECTname)) {  /*"SELECT * FROM newtable WHERE name = ? LIMIT 1"*/
            conn.setAutoCommit(false);

            preparedStatement.setString(1, name);  // так мы подставляем вместо знака вопроса нужный id
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                name = resultSet.getString(2); // получили имя
                String position = resultSet.getString(3); // получили поле position
                String date = resultSet.getString(4); // получили date
                user = new User(userId, name, position, date); //собираем юзера

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

    public static List<User> getUserByPosition(String position) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        List<User> arrayUsersByPosition = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECTposition)) {  /*"SELECT * FROM newtable WHERE position = ?*/
            conn.setAutoCommit(false);

            preparedStatement.setString(1, position);  // так мы подставляем вместо знака вопроса нужный position
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                String name = resultSet.getString(2); // получили имя
                position = resultSet.getString(3); // получили поле position
                String date = resultSet.getString(4); // получили date
                user = new User(userId, name, position, date); //собираем юзера

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
    }
    public static List<User> getUserByPosition(String position) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        List<User> arrayUsersByPosition = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECTposition)) {
            conn.setAutoCommit(false);

            preparedStatement.setString(1, position);  // так мы подставляем вместо знака вопроса нужный id
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                String name = resultSet.getString(2); // получили имя
                position = resultSet.getString(3); // получили поле position
                String date = resultSet.getString(4); // получили date
                user = new User(userId, name, position, date); //собираем юзера

                arrayUsersByPosition.add(user);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("<<<getUserByPosition>>> Запущен rollback()" + e);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return arrayUsersByPosition;
    }

    public void cleanUsersTable() throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

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
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        int userId;
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT MAX(id) FROM newtable")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            userId = resultSet.getInt(1); // получили id пользователя
        }
        return userId;
    }

    public static List<User> getUserByDate(String date) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        System.out.println("getTransactionIsolation: \n 1 = UNCOMMITTED  \n 2 = READ_COMMITTED \n 4 = REPEATABLE_READ \n 8 = SERIALIZABLE \n getTransactionIsolation: = " + conn.getTransactionIsolation());

        List<User> arrayUsersByDate = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SELECTdate)) {  /*"SELECT * FROM newtable WHERE date = ? LIMIT 1"*/
            conn.setAutoCommit(false);

            preparedStatement.setString(1, date);  // так мы подставляем вместо знака вопроса нужный id
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(1); // получили id пользователя
                String name = resultSet.getString(2); // получили имя
                String position = resultSet.getString(3); // получили поле position
                date = resultSet.getString(4); // получили date

                user = new User(userId, name, position, date);
                arrayUsersByDate.add(user);
            }
            conn.commit();
        } catch (SQLException e) {
            System.err.println("<<<getUserById>>> Запущен rollback()" + e);
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return arrayUsersByDate;
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