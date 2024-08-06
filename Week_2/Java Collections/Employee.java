import java.util.ArrayList;

class Employee {
    int id;
    String name;
    String address;
}

class EmployeeManagement {
    ArrayList<Employee> employees = new ArrayList<>();

    void addEmployee(Employee employee) {
        employees.add(employee);
    }

    void removeEmployee(int employeeId) {
        employees.removeIf(e -> e.id == employeeId);
    }

    void updateEmployee(int employeeId, String newAddress) {
        employees.stream().filter(e -> e.id == employeeId).forEach(e -> e.address = newAddress);
    }

    void displayEmployees() {
        employees.forEach(e -> System.out.println(e.id + " " + e.name + " " + e.address));
    }
}

class EmployeeManagementTest {
    public static void main(String[] args) {
        EmployeeManagement management = new EmployeeManagement();
        Employee e1 = new Employee();
        e1.id = 1;
        e1.name = "Alice";
        e1.address = "Address1";
        management.addEmployee(e1);
        management.displayEmployees();
        management.removeEmployee(1);
        management.displayEmployees();
        management.addEmployee(e1);
        management.updateEmployee(1, "NewAddress");
        management.displayEmployees();
    }
}
