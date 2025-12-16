package src;

import java.sql.*;

public class PaymentService {

    public boolean processPayment(int studentId, double amount) {
        String query = "INSERT INTO Payments (StudentID, PaymentAmount, PaymentStatus) VALUES (?, ?, 'Completed')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Set parameters
            ps.setInt(1, studentId);
            ps.setDouble(2, amount);

            // Execute the query
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

