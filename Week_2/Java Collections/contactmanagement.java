import java.util.Hashtable;

class Contact {
    int id;
    String name;
    String phoneNumber;
}

class ContactManagement {
    Hashtable<Integer, Contact> contacts = new Hashtable<>();

    void addContact(Contact contact) {
        contacts.put(contact.id, contact);
    }

    void removeContact(int contactId) {
        contacts.remove(contactId);
    }

    void displayContacts() {
        contacts.values().forEach(contact -> System.out.println(contact.id + " " + contact.name + " " + contact.phoneNumber));
    }
}

class ContactManagementTest {
    public static void main(String[] args) {
        ContactManagement management = new ContactManagement();
        Contact contact1 = new Contact();
        contact1.id = 1;
        contact1.name = "Alice";
        contact1.phoneNumber = "1234567890";
        management.addContact(contact1);
        Contact contact2 = new Contact();
        contact2.id = 2;
        contact2.name = "Bob";
        contact2.phoneNumber = "9876543210";
        management.addContact(contact2);
        management.displayContacts();
        management.removeContact(1);
        management.displayContacts();
    }
}
