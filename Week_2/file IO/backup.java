import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class BackupSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter source directory path:");
        String sourcePath = scanner.nextLine();
        System.out.println("Enter target directory path:");
        String targetPath = scanner.nextLine();
        
        Path sourceDir = Paths.get(sourcePath);
        Path targetDir = Paths.get(targetPath);
        
        DirectoryCopy.copyDirectory(sourceDir, targetDir);
        
        // Demonstrate error handling
        System.out.println("\nAttempting to copy to a read-only directory:");
        Path readOnlyDir = Paths.get("/root/read_only_dir");
        DirectoryCopy.copyDirectory(sourceDir, readOnlyDir);
        
        scanner.close();
    }
}

class FileCopy {
    public static void copyFile(Path source, Path target) {
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied: " + source + " to " + target);
        } catch (IOException e) {
            System.out.println("An error occurred while copying the file: " + e.getMessage());
        }
    }
}

class DirectoryCopy {
    public static void copyDirectory(Path sourceDir, Path targetDir) {
        try (Stream<Path> stream = Files.walk(sourceDir)) {
            stream.forEach(source -> {
                try {
                    Path target = targetDir.resolve(sourceDir.relativize(source));
                    if (Files.isDirectory(source)) {
                        Files.createDirectories(target);
                    } else {
                        FileCopy.copyFile(source, target);
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while copying: " + e.getMessage());
                }
            });
            System.out.println("Directory copied successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while walking the directory: " + e.getMessage());
        }
    }
}
