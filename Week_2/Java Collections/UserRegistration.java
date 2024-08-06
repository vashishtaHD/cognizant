import java.util.TreeSet;

public class UserRegistration {
    TreeSet<String> users = new TreeSet<>();

    void registerUser(String userName) {
        users.add(userName);
    }

    void removeUser(String userName) {
        users.remove(userName);
    }

    void displayUsers() {
        System.out.println(users);
    }
}

class UserRegistrationTest {
    public static void main(String[] args) {
        UserRegistration registration = new UserRegistration();
        registration.registerUser("Alice");
        registration.registerUser("Bob");
        registration.registerUser("Charlie");
        registration.displayUsers();
        registration.removeUser("Bob");
        registration.displayUsers();
    }
}
