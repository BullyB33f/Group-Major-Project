package src;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/ADGroupProject"; 
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "jamaican"; 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
