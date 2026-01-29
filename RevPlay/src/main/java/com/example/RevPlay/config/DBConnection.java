package com.example.RevPlay.config;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.*;

public class DBConnection {
	
	private static Logger log = LogManager.getLogger(DBConnection.class);
	
    public static Connection getConnection()  {
        String url = "jdbc:mysql://localhost:3306/revplay";
        String user = "root";
        String password = "2725";
        

        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(url, user, password);
//    		log.info("Database Connected successfully.");
        }catch (SQLException se){
        	log.error("Database not Connected Successfully. " + se);
//            System.out.println(se);
        }
        return connection;

    }

    public static Connection getInstance() {
        return getConnection();
    }
}
