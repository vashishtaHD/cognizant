// LogWriter.java
import java.io.*;

public class LogWriter {
    public static void writeLog(String fileName, String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(logMessage);
            writer.newLine();
            System.out.println("Log message written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the log file: " + e.getMessage());
        }
    }
}

// LogReader.java
import java.io.*;

public class LogReader {
    public static void readLogs(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Log contents:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Log file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the log file: " + e.getMessage());
        }
    }
}

// LogApp.java
public class LogApp {
    public static void main(String[] args) {
        String fileName = "application.log";
        
        // Writing logs
        LogWriter.writeLog(fileName, "Application started");
        LogWriter.writeLog(fileName, "User logged in");
        LogWriter.writeLog(fileName, "Data processed successfully");
        LogWriter.writeLog(fileName, "Application shutdown");
        
        // Reading logs
        LogReader.readLogs(fileName);
        
        // Demonstrating error handling
        System.out.println("\nAttempting to read from a non-existent file:");
        LogReader.readLogs("non_existent_file.log");
    }
}
