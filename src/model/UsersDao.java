/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class UsersDao {

    connect conn;

    public UsersDao() {
        conn = new connect();
    }

    public boolean login(Users user) {
        try {
            Connection c = conn.getConn();
            String sql = "Select * from users where userName='" + user.getUserName() + "' and password='" + user.getPassword() + " ' ";
            Statement ps = c.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean insert(Users user) {
        boolean b = false;
        try {
            Connection c = conn.getConn();
            CallableStatement cs = c.prepareCall("{call sp_insertUser(?,?,?,?,?,?) }");
            cs.setString(1, user.getUserName());
            cs.setString(2, user.getName());
            cs.setString(3, user.getLastName());
            cs.setString(4, user.getPhoneNumber());
            cs.setString(5, user.getEmail());
            cs.setString(6, user.getPassword());
            System.out.println(cs.executeUpdate());
            System.out.println("Register");            
            b = true;
            
        } catch (Exception e) {           
            System.out.println("An error");
        }
        return b;
    }

    public ArrayList<Users> listUsers() {
        ArrayList list = new ArrayList();
        Users user;
        try {
            Connection c = conn.getConn();
            PreparedStatement ps = c.prepareStatement("Select * from users");
            ResultSet rs = ps.executeQuery();
            System.out.println("Before");
            while (rs.next()) {
                user = new Users();
                user.setUserName(rs.getString(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setPhoneNumber(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassword(rs.getString(6));
                System.out.println("at 0: " + rs.getString(1));
                list.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean delete(Users user) {
        try {
            String sql = "Delete from users where userName='" + user.getUserName() + "'";
            Connection c = conn.getConn();
            Statement st = c.createStatement();
            if (st.executeUpdate(sql) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }
}
