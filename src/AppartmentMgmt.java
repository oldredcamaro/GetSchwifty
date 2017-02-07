package ua.kiev.prog;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * Created by Ilya on 07.02.2017.
 */
public class AppartmentMgmt {
    static Connection myConnection;


    Scanner scanner = new Scanner(in);

    public AppartmentMgmt(Connection myConnection) {
        this.myConnection = myConnection;
    }

    public AppartmentMgmt() {
    }

    public static void initDB() throws SQLException {
        Statement st = myConnection.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS Appartments");
            st.execute("CREATE TABLE Appartments (appId INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                                      "appDistrict VARCHAR(20), " +
                                                      "appAdress VARCHAR(200), " +
                                                      "appSquare FLOAT (4,2), " +
                                                      "appRoomAmount INT, " +
                                                      "appPrice FLOAT (9,2))");
        } finally {
            st.close();
        }
    }

    public void getAppByParams() throws SQLException {
        System.out.println("1:To see available appartments by the district");
        System.out.println("2:To see available appartments by the price");
        System.out.println("3:To see available appartments by its square");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                getAppByDistrict();
                break;
            case "2":
                getAppByPrice();
                break;
            case "3":
                getAppBySquare();
                break;
        }

    }

    public void getAppByDistrict() throws SQLException {
        System.out.println("Enter District name");
        String districtName = scanner.nextLine();
        String appDistrict = districtName.toUpperCase();
        String query = "Select * from Appartments where upper(appDistrict) = ?";
        viewData(query,appDistrict);
    }

    public void getAppByPrice() throws SQLException {
        System.out.println("Enter appartment price");
        String appPrice = scanner.nextLine();
        String query = "Select * from Appartments where appPrice > ?";
        viewData(query,appPrice);
    }

    public void getAppBySquare() throws SQLException {
        System.out.println("Enter appartment square");
        String appSquare = scanner.nextLine();
        String query = "Select * from Appartments where appSquare > ?";
        viewData(query,appSquare);
    }

    public void insertRandomAppartments() throws SQLException {


        myConnection.setAutoCommit(false); // enable transactions
        try {
            try {
                PreparedStatement ps = myConnection.prepareStatement("INSERT INTO Appartments (appDistrict, appAdress, appSquare, appRoomAmount, appPrice) VALUES(?, ?, ?, ?, ?)");
                try {
                    for (int i = 0; i < 10; i++) {
                        ps.setString(1, "Borshagovka Sector " + i);
                        ps.setString(2, "Test street " + i);
                        ps.setFloat(3, (float) (i+0.5));
                        ps.setInt(4,4);
                        ps.setFloat(5, (float) (i*100.5));

                        ps.executeUpdate();
                    }
                    myConnection.commit();
                } finally {
                    ps.close();
                }
            } catch (Exception ex) {
                myConnection.rollback();
            }
        } finally {
            myConnection.setAutoCommit(true); // return to default mode
        }
    }

    public void viewData(String inQuery, String fieldName) throws SQLException {
        PreparedStatement ps = myConnection.prepareStatement(inQuery);
        ps.setString(1,fieldName);
        try {
            // table of data representing a database result set,
            ResultSet rs = ps.executeQuery();
            try {
                // can be used to get information about the types and properties of the columns in a ResultSet object
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close(); // rs can't be null according to the docs
            }
        } finally {
            ps.close();
        }
    }
}

