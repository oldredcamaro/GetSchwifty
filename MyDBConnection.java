package ua.kiev.prog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Ilya on 07.02.2017.
 */
public class MyDBConnection {

    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/myawesomedb";
    private  static final String DB_USER = "root";
    private static final String DB_PASSWORD = "13241324";

    Connection myConnection ;

    public MyDBConnection() {
    }

    public static String getDbConnection() {
        return DB_CONNECTION;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    public Connection getConnection(){
        try{
            myConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myConnection;
    }
}
