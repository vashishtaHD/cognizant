package Week_2.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employee_db";
        String user = "voltox";
        String password = "12345";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established");
            connection.close();
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
    }
}
