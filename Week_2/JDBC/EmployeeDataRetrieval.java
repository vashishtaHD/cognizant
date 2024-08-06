package Week_2.JDBC;

import java.sql.*;

public class EmployeeDataRetrieval {
    public static void getEmployees() {
        String query = "SELECT * FROM employees";
        
        try (Connection conn = JDBCConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");
                
                System.out.printf("ID: %d, Name: %s, Position: %s, Salary: %.2f%n",
                                  id, name, position, salary);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getEmployees();
    }
}
