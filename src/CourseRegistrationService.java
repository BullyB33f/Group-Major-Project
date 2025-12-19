package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    


     public List<String> getStudentCourses(int studentId) {
        String query =
            "SELECT c.coursecode, c.coursename " +
            "FROM registrations r " +
            "JOIN courses c ON c.coursecode = r.coursecode " +
            "WHERE r.studentid = ? " +
            "ORDER BY c.coursecode";

        List<String> courses = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String code = rs.getString("coursecode");
                    String name = rs.getString("coursename");
                    courses.add(code + " - " + name);
                }
            }

        } catch (SQLException e) {
            System.out.println("Fetch courses failed: " + e.getMessage());
        }

        return courses;
    }
}

