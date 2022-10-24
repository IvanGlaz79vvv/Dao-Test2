package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Hello world!");


//        UserService.createTable(); //Создать таблицу
//
//        UserService.getAllUsers(); //Показать всех юзеров
//
        UserService.saveUser(); //Добавить юзера
//
//        UserService.getUserById(); //Выбрать юзера по id
//
//        UserService.getUserByName(); //Выбрать юзера по name
//
//        UserService.getUserByPosition(); //Выбрать юзера по position
//
//        UserService.getUserByDate(); //Выбрать юзера по date
//
//        UserService.removeUserById(); //Удалить юзера по id

// System.out.println("\nid: " + arrayUsersName.get(i).getId() + "\nname: " + arrayUsersName.get(i).getName() + "\nposition: " + arrayUsersName.get(i).getPosition() + "\ndate: " + arrayUsersName.get(i).getDate());

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        System.exit(0);
    }
}