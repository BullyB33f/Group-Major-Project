package src;

import java.sql.*;

public class CourseRegistrationService {

    public boolean registerCourse(int studentId, String courseCode) {
        String query = "INSERT INTO Registrations (StudentID, CourseCode) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Set parameters
            ps.setInt(1, studentId);
            ps.setString(2, courseCode);

            // Execute the query
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean dropCourse(int studentId, String courseCode) {
        String query = "DELETE FROM Registrations WHERE StudentID = ? AND CourseCode = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            // Set parameters
            ps.setInt(1, studentId);
            ps.setString(2, courseCode);

            // Execute the query
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

