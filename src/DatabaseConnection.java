package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection is a utility class responsible for creating and returning
 * a JDBC Connection to the PostgreSQL database.
 *
 * Any service class (StudentService, PaymentService, CourseRegistrationService, etc.)
 * calls getConnection() whenever it needs to execute SQL commands.
 */
public class DatabaseConnection {

    // JDBC URL format: jdbc:postgresql://HOST:PORT/DATABASE_NAME
    private static final String URL = "jdbc:postgresql://localhost:5432/ADGroupProject";

    // PostgreSQL username used to connect to the database
    private static final String USER = "postgres";

    // PostgreSQL password used to connect to the database
    private static final String PASSWORD = "jamaican";

    /**
     * reates and returns a new Connection to the database.
     *
     * @return Connection object if successful
     * @throws SQLException if the connection fails (wrong credentials, DB down, etc.)
     */
    public static Connection getConnection() throws SQLException {

        // DriverManager uses the URL, username, and password to establish a DB connection.
        // The PostgreSQL JDBC driver must be on the classpath for this to work.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
