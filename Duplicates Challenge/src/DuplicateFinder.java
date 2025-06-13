import java.util.ArrayList;
import java.util.List;

public class DuplicateFinder {

    public static List<String[]> findDuplicates(List<Contact> contacts){

        /*
            Note: for larger datasets, performance could be improved
            we could, for example, group by email (since email its the strongest identifier to determine if its the same person) and it would reduce the number of comparisons.

            I'll use a nested for loop for clarity and simplicity. Ensuring all contact pairs are considered.
        */
        List<String[]> list = new ArrayList<>();

        for(int i=0; i<contacts.size(); i++){
            Contact contact1 = contacts.get(i);
            for(int j=i+1; j<contacts.size(); j++){
                Contact contact2 = contacts.get(j);

                int matchScore = calculateMatchScore(contact1, contact2);
                String accuracy = getAccuracy(matchScore);
                if(!accuracy.equals("None")){
                    list.add(new String[] {contact1.getContactId(), contact2.getContactId(), accuracy});
                }
            }
        }

        return list;
    }

    private static int countMatchingChars(String a, String b){
        int count = 0;
        int minLength = Math.min(a.length(), b.length());
        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) == b.charAt(i)) count++;
            else break;
        }
        return count;
    }

    private static int calculateMatchScore(Contact contact1, Contact contact2){
        int score = 0;

        //Compare Email
        String contact1Email = contact1.getEmail();
        String contact2Email = contact2.getEmail();

        if(!contact1Email.isEmpty() && contact1Email.equals(contact2Email)) score+=5;
        else if(!contact1Email.isEmpty() && !contact2Email.isEmpty()){
            int index1 = contact1Email.indexOf('@');
            int index2 = contact2Email.indexOf('@');
            String localPartContact1 = contact1Email.substring(0, index1);
            String localPartContact2 = contact2Email.substring(0, index2);

            if(localPartContact1.equals(localPartContact2)) score+=1;
        }

        //Compare First Name
        if(!contact1.getFirstName().isEmpty() && contact1.getFirstName().equals(contact2.getFirstName())) score+=2;
        else if(countMatchingChars(contact1.getFirstName(), contact2.getFirstName()) >=3) score+=1;

        //Compare Last Name
        if(!contact1.getLastName().isEmpty() && contact1.getLastName().equals(contact2.getLastName())) score+=3;
        else if(countMatchingChars(contact1.getLastName(), contact2.getLastName()) >=3) score+=1;

        //Compare Zip Code
        if(!contact1.getZipCode().isEmpty() && contact1.getZipCode().equals(contact2.getZipCode())) score+=1;

        //Compare Address
        if(!contact1.getAddress().isEmpty() && contact1.getAddress().equals(contact2.getAddress())) score+=2;
        else if(!contact1.getAddress().isEmpty() && !contact2.getAddress().isEmpty() && contact1.getAddress().contains(contact2.getAddress()) || contact2.getAddress().contains(contact1.getAddress())) score+=1;

        return score;
    }

    private static String getAccuracy(int score){
        if(score >= 8) return "High";
        else if(score >= 5) return "Medium";
        else if(score >= 2) return "Low";
        else return "None";
    }
}