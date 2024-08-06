package Week_2.JDBC;

import java.sql.*;

public class EmployeeCRUDOperations {
    public static void addEmployee(String name, String position, double salary) {
        String query = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, position);
            pstmt.setDouble(3, salary);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " employee(s) added.");
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void updateEmployee(int id, String name, String position, double salary) {
        String query = "UPDATE employees SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, position);
            pstmt.setDouble(3, salary);
            pstmt.setInt(4, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " employee(s) updated.");
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " employee(s) deleted.");
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Demonstrate CRUD operations
        addEmployee("John Doe", "Developer", 75000);
        addEmployee("Jane Smith", "Manager", 85000);
        updateEmployee(1, "John Doe", "Senior Developer", 80000);
        deleteEmployee(2);
        EmployeeDataRetrieval.getEmployees();
    }
}
