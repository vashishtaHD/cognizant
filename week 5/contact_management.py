import pickle
import os

# Text file operations
def read_contacts_text(filename):
    try:
        with open(filename, 'r') as file:
            return [line.strip() for line in file]
    except FileNotFoundError:
        print(f"Error: File '{filename}' not found.")
        return []
    except IOError:
        print(f"Error: Unable to read file '{filename}'.")
        return []

def write_contacts_text(filename, contacts):
    try:
        with open(filename, 'w') as file:
            for contact in contacts:
                file.write(f"{contact}\n")
    except IOError:
        print(f"Error: Unable to write to file '{filename}'.")

# Binary file operations
def save_contacts_binary(filename, contacts):
    try:
        with open(filename, 'wb') as file:
            pickle.dump(contacts, file)
    except IOError:
        print(f"Error: Unable to write to binary file '{filename}'.")

def load_contacts_binary(filename):
    try:
        with open(filename, 'rb') as file:
            return pickle.load(file)
    except FileNotFoundError:
        print(f"Error: Binary file '{filename}' not found.")
        return []
    except (IOError, pickle.UnpicklingError):
        print(f"Error: Unable to read or corrupt data in binary file '{filename}'.")
        return []

# User interaction functions
def add_contact(contacts, contact):
    contacts.append(contact)
    print(f"Contact '{contact}' added successfully.")

def remove_contact(contacts, contact):
    if contact in contacts:
        contacts.remove(contact)
        print(f"Contact '{contact}' removed successfully.")
    else:
        print(f"Error: Contact '{contact}' not found.")

def display_contacts(contacts):
    if contacts:
        print("Contacts:")
        for contact in contacts:
            print(f"- {contact}")
    else:
        print("No contacts found.")

def main():
    text_file = "contacts.txt"
    binary_file = "contacts.bin"
    contacts = []

    # Load existing contacts
    if os.path.exists(text_file):
        contacts = read_contacts_text(text_file)
    elif os.path.exists(binary_file):
        contacts = load_contacts_binary(binary_file)

    while True:
        print("\nContact Management System")
        print("1. Add Contact")
        print("2. Remove Contact")
        print("3. Display Contacts")
        print("4. Save and Exit")

        choice = input("Enter your choice (1-4): ")

        if choice == '1':
            contact = input("Enter contact name: ")
            add_contact(contacts, contact)
        elif choice == '2':
            contact = input("Enter contact name to remove: ")
            remove_contact(contacts, contact)
        elif choice == '3':
            display_contacts(contacts)
        elif choice == '4':
            write_contacts_text(text_file, contacts)
            save_contacts_binary(binary_file, contacts)
            print("Contacts saved. Exiting...")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()