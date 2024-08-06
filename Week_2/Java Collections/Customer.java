import java.util.TreeMap;

class Customer {
    int id;
    String name;
    String email;
}

class CustomerAccounts {
    TreeMap<Integer, Customer> accounts = new TreeMap<>();

    void addCustomer(Customer customer) {
        accounts.put(customer.id, customer);
    }

    void removeCustomer(int customerId) {
        accounts.remove(customerId);
    }

    void displayCustomers() {
        accounts.values().forEach(customer -> System.out.println(customer.id + " " + customer.name + " " + customer.email));
    }
}

class CustomerAccountsTest {
    public static void main(String[] args) {
        CustomerAccounts accounts = new CustomerAccounts();
        Customer customer1 = new Customer();
        customer1.id = 1;
        customer1.name = "Alice";
        customer1.email = "alice@example.com";
        accounts.addCustomer(customer1);
        Customer customer2 = new Customer();
        customer2.id = 2;
        customer2.name = "Bob";
        customer2.email = "bob@example.com";
        accounts.addCustomer(customer2);
        accounts.displayCustomers();
        accounts.removeCustomer(1);
        accounts.displayCustomers();
    }
}
