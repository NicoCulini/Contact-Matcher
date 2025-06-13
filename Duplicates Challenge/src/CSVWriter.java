import java.io.*;
import java.util.*;

public class CSVWriter {

    public static void writeDuplicates(String fileName, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("ContactId Source,ContactId Match,Accuracy");
            bw.newLine();
            for (String[] line : data) {
                bw.write(String.join(",", line));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
