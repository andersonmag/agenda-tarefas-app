package com.bwn.todo.database.connection;

import java.sql.*;

public class DBConnection {

    private static String url = "jdbc:postgresql://localhost:5432/todo_db";
    private static String user = "";
    private static String password = "";
    private static Connection connection;

    static {
        connect();
    }

    public static void connect() {
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}