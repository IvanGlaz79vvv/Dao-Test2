package jm.task.core.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jm.task.core.jdbc.service.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {

    //    static String path = "C:\\Users\\wk\\Desktop\\Квартира.txt";
    static String path = "C:\\Users\\wk\\Desktop\\Квартира.xls";

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {


        System.out.println("Hello world! \n");


        /*HashMap < String, Integer> map = new HashMap<>();
        map.put("KING", 100);
        System.out.println("KING".hashCode());*/

//        UserService.createTable(); //Создать таблицу

//        System.out.println(UserService.addFromExcel(/*path*/));



//        Map map = UserService.saveUserFromExcelXlsX();
//        System.out.println("\n\n\n<<<<<<<<<<<<<< Print Map: >>>>>>>>>>>>>>> \n\n" + map);
//        Iterator iterator = UserService.saveUserFromExcelXlsX().values().iterator();
//        while (iterator.hasNext())
//        {
//            System.out.println(map);
//        }


        UserService.saveUser(); //Добавить юзера
//
//        UserService.getAllUsers(); //Показать всех юзеров
//
//        UserService.getUserById(); //Выбрать юзера по id
//
//        UserService.getUserByName(); //Выбрать юзера по name
//
//        UserService.getUserByPosition(); //Выбрать юзера по position
//
//        UserService.getUserByFlight_id(); //Выбрать юзера по Flight_id
//
//        UserService.removeUserById(); //Удалить юзера по id


//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        System.exit(0);
    }
}