package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * StudentService handles all database operations related to Students.
 * This class uses JDBC to insert and manage student records in PostgreSQL.
 */
public class StudentService {

    /**
     * Adds a new student record into the Students table.
     *
     * @param studentId        Unique ID for the student (Primary Key)
     * @param firstName        Student first name
     * @param lastName         Student last name
     * @param email            Student email (must be unique)
     * @param dateOfBirth      Student date of birth (YYYY-MM-DD)
     * @param enrollmentDate   Enrollment date (YYYY-MM-DD)
     * @return true if the insert succeeds, false otherwise
     */
    public boolean addStudent(int studentId,
                              String firstName,
                              String lastName,
                              String email,
                              String dateOfBirth,      // format: YYYY-MM-DD
                              String enrollmentDate) {  // format: YYYY-MM-DD

        // SQL statement to insert a new student record into the Students table.
        // The '?' are placeholders that will be safely replaced using PreparedStatement.
        String sql = """
            INSERT INTO Students (StudentID, FirstName, LastName, Email, DateOfBirth, EnrollmentDate)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        /*
         * Try-with-resources ensures that both the Connection and PreparedStatement
         * are automatically closed after execution (prevents resource leaks).
         */
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Bind input values into the SQL placeholders.
            ps.setInt(1, studentId);                           // StudentID
            ps.setString(2, firstName);                        // FirstName
            ps.setString(3, lastName);                         // LastName
            ps.setString(4, email);                            // Email

            // Convert string dates to java.sql.Date to match PostgreSQL DATE fields.
            ps.setDate(5, java.sql.Date.valueOf(dateOfBirth)); // DateOfBirth
            ps.setDate(6, java.sql.Date.valueOf(enrollmentDate)); // EnrollmentDate

            // Execute insert and return true if at least one row was inserted.
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            // Handles database errors (e.g., duplicate StudentID, duplicate Email, FK issues, etc.)
            System.out.println("Add student failed: " + e.getMessage());
            return false;

        } catch (IllegalArgumentException e) {
            // Handles invalid date format passed into Date.valueOf(...)
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
            return false;
        }
    }
}
