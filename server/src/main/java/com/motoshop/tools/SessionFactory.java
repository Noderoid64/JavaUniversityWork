package com.motoshop.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SessionFactory {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost";
    private static final String DB_MOTORSHOP_URL = "jdbc:postgresql://localhost/motoshop";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "qwertyui";

    static {
        try{
            Class.forName(JDBC_DRIVER);
        }catch(Exception e){
            System.err.println("Exception while initializing hibernate util.. ");
            e.printStackTrace();
        }
    }

    public static Connection getSystemConnection() {
        try {
            return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_MOTORSHOP_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
