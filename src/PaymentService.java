package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * PaymentService handles database operations related to Payments.
 * This class uses JDBC to insert payment transactions into the PostgreSQL database.
 */
public class PaymentService {

    /**
     * Processes a payment for a student by inserting a record into the Payments table.
     *
     * @param studentId The ID of the student making the payment (FK to Students.StudentID)
     * @param amount    The payment amount to be recorded
     * @return true if the payment insert succeeds, false otherwise
     */
    public boolean processPayment(int studentId, double amount) {

        // SQL statement to insert a payment record.
        // PaymentStatus is set to 'Completed' by default in this transaction.
        String query =
                "INSERT INTO Payments (StudentID, PaymentAmount, PaymentStatus) " +
                "VALUES (?, ?, 'Completed')";

        /*
         * Try-with-resources ensures that both the Connection and PreparedStatement
         * are automatically closed after execution (prevents resource leaks).
         */
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Bind values to the placeholders (?) safely to prevent SQL injection.
            ps.setInt(1, studentId);    // StudentID
            ps.setDouble(2, amount);    // PaymentAmount

            // Execute the INSERT and store how many rows were affected.
            int rowsAffected = ps.executeUpdate();

            // If at least 1 row was inserted, the payment was successfully processed.
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Handles database errors (e.g., invalid StudentID, constraint violations, etc.)
            System.out.println("Payment failed: " + e.getMessage());
            return false;
        }
    }
}
