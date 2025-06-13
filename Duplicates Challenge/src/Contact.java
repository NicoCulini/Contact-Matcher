public class Contact {
    private String contactId, firstName, lastName, email, zipCode, address;

    public Contact(String contactId, String firstName, String lastName, String email, String zipCode, String address){
        this.contactId = contactId;
        this.firstName = firstName != null ? firstName.trim().toLowerCase() : "";
        this.lastName = lastName != null ? lastName.trim().toLowerCase() : "";
        this.email = email != null ? normalizeEmail(email) : "";
        this.zipCode = zipCode != null ? zipCode.trim() : "";
        this.address = address != null ? address.trim().toLowerCase() : "";
    }


    /*
        Function to normalize emails
        If we have the following emails:
          -nicolas.culini@gmail.com
          -nicolasculini@gmail.com
          -nicolas.culini+netflix@gmail.com
      
        They clearly belong to the same person (and first and second are trated as the same email),
        but since many characters differ, it would damage the confidence of the matching, so we normalize them
     */
    public static String normalizeEmail(String email){
        email = email.trim().toLowerCase();
        int index = email.indexOf('@');

        if(index == -1){
            return "";
        }

        String local = email.substring(0, index);
        String domain = email.substring(index);

        local = local.replace(".","");

        int alias = local.indexOf('+');
        if(alias != -1){
            local = local.substring(0, alias);
        }

        return (local + domain);
    }

    //Getters
    public String getContactId(){
        return contactId;
    }
    public String getFirstName(){
        return firstName; 
    }
    public String getLastName(){
        return lastName; 
    }
    public String getEmail(){
        return email; 
    }
    public String getZipCode(){
        return zipCode; 
    }
    public String getAddress(){
        return address; 
    }
}
