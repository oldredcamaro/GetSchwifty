package ua.kiev.prog;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ilya on 07.02.2017.
 */
public class Main {

    public static void main(String[] args) {
        Connection myConnection = new MyDBConnection().getConnection();

        AppartmentMgmt appartmentMgmt = new AppartmentMgmt(myConnection);
        try {
            appartmentMgmt.initDB();
            appartmentMgmt.insertRandomAppartments();
            appartmentMgmt.getAppByParams();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
