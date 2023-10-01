import java.lang.Comparable;

public class Contact implements Comparable<Contact> {

    private String firstName;
    private String lastName;
    private String name;
    private int phoneNumber;
    private String emailAddress;
    private String adress;
    private String birthday;
    private String notes;

    public Contact(String firstName, String lastName, int phoneNumber, String emailAddress, String adress,
            String birthday, String notes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.adress = adress;
        this.birthday = birthday;
        this.notes = notes;
        name = firstName + " " + lastName;
    }

    public int compareTo(Contact otherContact) {
        return this.name.compareTo(otherContact.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return "Name: " + name + "\n" +
                "Phone number: " + phoneNumber + "\n" +
                "Email address: " + emailAddress + "\n" +
                "Birthday: " + birthday + "\n" +
                "Notes: " + notes;
    }

}
