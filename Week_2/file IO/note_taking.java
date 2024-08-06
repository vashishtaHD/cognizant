// FileWriterDemo.java
import java.io.*;

public class FileWriterDemo {
    public static void saveNoteToFile(String fileName, String note) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(note);
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

// FileReaderDemo.java
import java.io.*;

public class FileReaderDemo {
    public static void readNoteFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Note content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}

// NoteApp.java
import java.util.Scanner;

public class NoteApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter your note:");
        String note = scanner.nextLine();
        
        String fileName = "note.txt";
        
        FileWriterDemo.saveNoteToFile(fileName, note);
        FileReaderDemo.readNoteFromFile(fileName);
        
        scanner.close();
    }
}
