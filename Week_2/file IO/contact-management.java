import java.io.*;
import java.util.Scanner;

public class ContactManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter contact name:");
        String name = scanner.nextLine();
        System.out.println("Enter contact phone:");
        String phone = scanner.nextLine();
        System.out.println("Enter contact email:");
        String email = scanner.nextLine();
        
        Contact contact = new Contact(name, phone, email);
        String fileName = "contact.ser";
        
        ContactWriter.saveContact(fileName, contact);
        Contact readContact = ContactReader.readContact(fileName);
        
        if (readContact != null) {
            System.out.println("Read contact: " + readContact);
        }
        
        // Demonstrate error handling
        System.out.println("\nAttempting to read from a non-existent file:");
        ContactReader.readContact("non_existent_file.ser");
        
        scanner.close();
    }
}

class Contact implements Serializable {
    private String name;
    private String phone;
    private String email;
    
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    // Getters and setters omitted for brevity
    
    @Override
    public String toString() {
        return "Contact{name='" + name + "', phone='" + phone + "', email='" + email + "'}";
    }
}

class ContactWriter {
    public static void saveContact(String fileName, Contact contact) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contact);
            System.out.println("Contact saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the contact: " + e.getMessage());
        }
    }
}

class ContactReader {
    public static Contact readContact(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Contact contact = (Contact) ois.readObject();
            System.out.println("Contact read successfully.");
            return contact;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the contact: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        return null;
    }
}
