package homeWork;
import java.io.*;
import java.util.Random;

public class FileGenerator {

    public static void generateFile(String fileName, int numValues) {
        try {
            // Read existing values from the file
            StringBuilder existingValues = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    existingValues.append(line).append(",");
                }
            } catch (FileNotFoundException ignored) {
                // File doesn't exist yet, which is okay
            }

            // Append new values to the existing values
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(existingValues.toString());

                Random random = new Random();
                for (int i = 0; i < numValues; i++) {
                    int value = random.nextInt(300) + 1; // Change the range as needed
                    writer.write(Integer.toString(value));
                    if (i < numValues - 1) {
                        writer.write(",");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Generate Source.txt with 10,000 values
        generateFile("C:\\Users\\Hksel\\IdeaProjects\\homework\\src\\homeWork\\Source.txt", 10000);

        // Generate Search.txt with 10,000 values
        generateFile("C:\\Users\\Hksel\\IdeaProjects\\homework\\src\\homeWork\\Search.txt", 10000);
    }
}