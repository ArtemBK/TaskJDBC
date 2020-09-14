package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mytestdb?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root2";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL, LOGIN, PASSWORD);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("error - no connection");
        }
        return connection;
    }
}
