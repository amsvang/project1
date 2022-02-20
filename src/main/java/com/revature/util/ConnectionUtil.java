package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.revature.util.LoggingSingletonUtil;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {
        LoggingSingletonUtil logger = LoggingSingletonUtil.getLogger();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//Amy DB
        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");

          //TEST
//        logger.setWriteToFile(true);
//        logger.debug("TEST1: " + url);
//        logger.debug("TEST2: " + username);
//        logger.debug("TEST3: " + password);
//        logger.setWriteToFile(false);
        return DriverManager.getConnection(url ,username, password);

////Niko DB
//         String url = System.getenv("DB_URL");
//         String username = System.getenv("DB_USER");
//         String password = System.getenv("DB_PW");
//         return DriverManager.getConnection(url ,username, password);


    }



}
