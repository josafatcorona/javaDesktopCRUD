package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {

    public static Connection conn;
    private static final String driver ="com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "";
    private static final String url = "jdbc:mysql://localhost/store";

    public connect() {
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void no() throws SQLException{
        conn.close();
    }

    public Connection getConn() {
        System.out.println("Connection sucessfully");
        return conn;
    }
    
}
