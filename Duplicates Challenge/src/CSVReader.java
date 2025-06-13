import java.io.*;
import java.util.*;

public class CSVReader {

    public static List<Contact> readContacts(String fileName) {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (fields.length >= 6) {
                    contacts.add(new Contact(
                        fields[0],
                        fields[1],
                        fields[2],
                        fields[3],
                        fields[4],
                        fields[5]
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}