package jm.task.core.jdbc.Utils;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadproperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        System.out.println("PropertiesUtil.get отработал");
        return PROPERTIES.getProperty(key);
    }

    public static void loadproperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().
                getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
            System.out.println("loadproperties отработал");
        } catch (IOException e) {
            System.out.println("<<<<<loadproperties: IOException e>>>>>\n" + e);
        }finally {

        }
    }
}