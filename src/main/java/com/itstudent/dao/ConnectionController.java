package com.itstudent.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionController {

    public static Connection connection;
    public static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
         connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

        if (connection != null) {
            System.out.println("DB is connected");
            return connection;
        }
        System.out.println("Fail to connect");
        return null;
    }
    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
            if (connection.isClosed())
                System.out.println("Connection had been closed");
        }
    }
}
