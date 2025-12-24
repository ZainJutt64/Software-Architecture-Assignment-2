package healthcare.util;
import java.io.*;
import java.util.*;

public class CSVUtil {
    public static List<String[]> readCSV(String filename) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    data.add(line.split(","));
                }
            }
        } catch (Exception e) {
            System.out.println("File not found or empty: " + filename);
        }
        return data;
    }

    public static void writeCSV(String filename, List<String[]> data, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, append))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}