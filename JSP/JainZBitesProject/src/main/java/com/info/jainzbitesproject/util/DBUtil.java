package com.info.jainzbitesproject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/jainzbitesprojectdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; 

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        try { 
        	if (rs != null) 
        		rs.close(); } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        try {
        	if (ps != null) 
        		ps.close(); } 
        catch (Exception e) {
        	e.printStackTrace();
        }
        try {
        	if (con != null) 
        		con.close(); }
            catch (Exception e) {}
    }
}
