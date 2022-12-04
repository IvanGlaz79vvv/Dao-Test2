package jm.task.core.jdbc;

import jm.task.core.jdbc.SwingPackage.SwingClass;
import jm.task.core.jdbc.Utils.Util;
import jm.task.core.jdbc.service.InClass;
import jm.task.core.jdbc.service.UserService;
import org.apache.poi.examples.hssf.usermodel.HSSFReadWrite;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

public class Main {

    //    static String path = "C:\\Users\\wk\\Desktop\\Квартира.txt";
    static String path = "C:\\Users\\wk\\Desktop\\Квартира.xls";

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {


        System.out.println("Hello world!");


        /*HashMap < String, Integer> map = new HashMap<>();
        map.put("KING", 100);
        System.out.println("KING".hashCode());*/

//        UserService.createTable(); //Создать таблицу

//        System.out.println(UserService.addFromExcel(/*path*/));



        Map map = UserService.saveUserFromExcelXlsX();
        map.remove("A1");
        map.remove("B1");
        map.remove("C1");
        map.remove("D1");
        map.remove("E1");
        map.remove("F1");
//        System.out.println("\n\n\n<<<<<<<<<<<<<< Print Map: >>>>>>>>>>>>>>> \n\n" + map);
        Iterator iterator = map.values().iterator();
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }


//        UserService.saveUser(); //Добавить юзера
//
//        UserService.getAllUsers(); //Показать всех юзеров
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


//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        System.exit(0);
    }
}