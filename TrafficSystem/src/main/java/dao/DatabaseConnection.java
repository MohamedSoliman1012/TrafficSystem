package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    //make sure to change these based on what you acually use
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    private static final String USER = "postgres";
    private static final String PASSWORD = "mohamed";
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully");
            } catch (SQLException e) {
                System.out.println("Error connecting to database: " + e.getMessage());
            }
        }
        return connection;
    }
    
    public static void closeConnection() throws SQLException {
        if (connection != null) {
        }
    }
}