package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//Amy DB
//        String url = System.getenv("DB_URL");
//        String username = System.getenv("DB_USER");
//        String password = System.getenv("DB_PASS");
//        return DriverManager.getConnection(url ,username, password);

//Niko DB
        String url = System.getenv("NikoDB_URL");
        String username = System.getenv("NikoDB_USER");
        String password = System.getenv("NikoDB_PW");
        return DriverManager.getConnection(url ,username, password);


    }



}
