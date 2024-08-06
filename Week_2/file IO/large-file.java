import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LargeFileProcessor {
    public static void main(String[] args) {
        String inputFileName = "sales_data.txt";
        String outputFileName = "processed_sales_data.txt";
        
        List<String> processedData = LargeFileReader.readLargeFile(inputFileName);
        LargeFileWriter.writeProcessedData(outputFileName, processedData);
        
        // Demonstrate error handling
        System.out.println("\nAttempting to read from a non-existent file:");
        LargeFileReader.readLargeFile("non_existent_file.txt");
        
        System.out.println("\nAttempting to write to a restricted directory:");
        LargeFileWriter.writeProcessedData("/root/restricted_file.txt", processedData);
    }
}

class LargeFileReader {
    public static List<String> readLargeFile(String inputFileName) {
        List<String> processedData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line (e.g., parse and print sales records)
                String processedLine = processLine(line);
                processedData.add(processedLine);
                System.out.println(processedLine);
            }
            System.out.println("File read successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return processedData;
    }
    
    private static String processLine(String line) {
        // Simple processing: just append " - Processed"
        return line + " - Processed";
    }
}

class LargeFileWriter {
    public static void writeProcessedData(String outputFileName, List<String> processedData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String data : processedData) {
                writer.write(data);
                writer.newLine();
            }
            System.out.println("Processed data written successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file: " + e.getMessage());
        }
    }
}
