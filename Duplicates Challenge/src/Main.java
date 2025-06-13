import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputCsv = "resources/contacts.csv";
        String outputCsv = "output/duplicates.csv";

        List<Contact> contacts = CSVReader.readContacts(inputCsv);
        List<String[]> duplicates = DuplicateFinder.findDuplicates(contacts);
        CSVWriter.writeDuplicates(outputCsv, duplicates);
    }
}