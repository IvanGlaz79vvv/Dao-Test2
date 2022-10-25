package jm.task.core.jdbc;

import jm.task.core.jdbc.Dao.Dao;
import jm.task.core.jdbc.Model.User;
import jm.task.core.jdbc.service.InClass;
import jm.task.core.jdbc.service.UserService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    static String path = "C:\\Users\\wk\\Desktop\\Квартира.txt";

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {

//        System.out.println("Hello world!");


//        UserService.createTable(); //Создать таблицу

//        UserService.addFromFile();
//
//        UserService.getAllUsers(); //Показать всех юзеров
//
//        UserService.saveUser(); //Добавить юзера
//
//        UserService.getUserById(); //Выбрать юзера по id
//
        UserService.getUserByName(); //Выбрать юзера по name
//
//        UserService.getUserByPosition(); //Выбрать юзера по position
//
//        UserService.getUserByDate(); //Выбрать юзера по date
//
//        UserService.removeUserById(); //Удалить юзера по id

//        String string = UserService.inputFile();

//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        System.exit(0);
    }
}