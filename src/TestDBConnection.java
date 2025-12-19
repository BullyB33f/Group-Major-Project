package src;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * TestDBConnection is a simple test class used to verify that the application
 * can successfully connect to the PostgreSQL database through DatabaseConnection.
 *
 * This is usually run once during setup/troubleshooting to confirm that:
 * - The JDBC URL is correct
 * - Username/password are correct
 * - PostgreSQL server is running
 * - PostgreSQL JDBC driver is on the classpath
 */
public class TestDBConnection {

    public static void main(String[] args) {

        // Try to connect to the database
        try {
            // Get a database connection using the DatabaseConnection utility class
            Connection connection = DatabaseConnection.getConnection();

            // If connection succeeds, this message will print
            System.out.println("Connected successfully");

            // Close the connection after testing to free up resources
            connection.close();

        } catch (SQLException e) {
            // If connection fails, print the error message (e.g., wrong password, DB not running, etc.)
            System.out.println("Error: " + e.getMessage());
        }
    }
}
