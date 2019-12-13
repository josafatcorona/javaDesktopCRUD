/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author josaf
 */
public class crud extends connect {

    private Connection conn = getConn();
    private String Squery = "";
    private Statement st = null;
    private ResultSet rs = null;

    public boolean login(Users user) {
        try {
            st = conn.createStatement();
            System.out.println(user.getUserName()+" : "+ user.getPassword());
            Squery = "Select * from users where userName ='" + user.getUserName() + "' && password = '" + user.getPassword() + "'  ";
            rs = st.executeQuery(Squery);
            if (rs.next()) {
                System.out.println(rs.first());
                return true;
            }else{
                System.out.println("No first");
                return false;                
            }

        } catch (Exception e) {
            return false;
        }

    }
}
