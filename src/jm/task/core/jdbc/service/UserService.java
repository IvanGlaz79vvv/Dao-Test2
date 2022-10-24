package jm.task.core.jdbc.service;

import jm.task.core.jdbc.Dao.Dao;
import jm.task.core.jdbc.Model.User;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class UserService {

    public static void createTable() throws SQLException {
        Dao.createTable();
        System.exit(0);
    }

    public static void getAllUsers() throws SQLException {
        List<User> allUsers = Dao.getAllUsers();
        System.out.println("Всего юзеров: " + allUsers.size());
        for (User printuser : allUsers) {
            System.out.println(printuser);
        }
        System.exit(0);
    }

    public static void saveUser() throws IOException, SQLException {

        System.out.println("Имя:");
        String name = InClass.inputString();  //bufferedReader.readLine();
        System.out.println("Дата заезда:");
        String position = InClass.inputString();
        System.out.println("Дата:");
        String date = InClass.inputString();

        try {
            Dao.saveUser(name, position, date);
        } catch (SQLException e) {
            System.err.println("<<<UserService>>> <<<Dao.saveUser>>>: " + e);
        }
        int userId = Dao.getLastdId();

        User user = Dao.getUserById(userId);
        System.out.println("Вы создали юзера:\n" + user);

        System.exit(0);
    }

    public static void getUserById() throws SQLException, IOException {

        System.out.println("Выбери id юзера:");
        int id = InClass.inputInteger();

        User user = Dao.getUserById(id);
        System.out.println(user);

        System.out.println();

        System.exit(0);
    }

    public static void getUserByName() throws SQLException, IOException {
        System.out.println("Выбери name юзера:");

        String name = InClass.inputString();

        List<User> arrayUsersName = Dao.getUserByName(name);
        System.out.println("Количество юзеров: " + arrayUsersName.size());
        for (User print : arrayUsersName) {
            System.out.println(print);
        }

        System.exit(0);
    }

    public static void getUserByPosition() throws SQLException, IOException {
        System.out.println("Выбери position юзера:");

        String position = InClass.inputString();

        List<User> arrayUsersPosition = Dao.getUserByPosition(position);
        System.out.println("Количество юзеров: " + arrayUsersPosition.size());
        for (User print : arrayUsersPosition) {
            System.out.println(print);
        }

        System.exit(0);
    }

    public static void getUserByDate() throws SQLException, IOException {
        System.out.println("Выбери date юзера:");

        String date = InClass.inputString();

        List<User> arrayUsersDate = Dao.getUserByDate(date);
        System.out.println("Количество юзеров: " + arrayUsersDate.size());
        for (int i = 0; i < arrayUsersDate.size(); i++) {
            System.out.println(arrayUsersDate.get(i));
        }

        System.exit(0);
    }

    public static void removeUserById() throws SQLException, IOException {
        System.out.println("Выбери id юзера для удаления:");

        Scanner scannerRemove = new Scanner(System.in);
        scannerRemove = new Scanner(System.in);
        int id = InClass.inputInteger();

        Dao.removeUserById(id);

        System.exit(0);
    }
}