import java.lang.Comparable;

public class Contact implements Comparable {
    private String name;
    private int phoneNumber;
    private String emailAddress;
    private String adress;
    private String birthday;
    private String notes;

    public Contact(String name, int phoneNumber, String emailAddress, String adress, String birthday, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.adress = adress;
        this.birthday = birthday;
        this.notes = notes;
    }

    public int compareTo(Object otherContact) {
        return this.name.compareTo(((Contact) otherContact).name);
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
}
