import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        LinkedlistADT phoneBook = new LinkedlistADT();
        listAllEvents eventList = new listAllEvents();
        int option = 0;
        Scanner input = new Scanner(System.in);

        do {

            System.out.println("Hello! Welcome to your phonebook, please choose an option:");
            System.out.println("1- Add a contact");
            System.out.println("2- Search for a contact");
            System.out.println("3- Delete a contact");
            System.out.println("4- Schedule an event");
            System.out.println("5- Print event details");
            System.out.println("6- Print contacts by first name");
            System.out.println("7- Print all event in alphabetical order");
            System.out.println("8- Exit");
            System.out.print("Choose an option: ");
            option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter the contact's name: ");
                    String fName = input.next();
                    System.out.print(" ");
                    String lName = input.next();
                    System.out.println();
                    System.out.print("Enter the contact's phone number: ");
                    int pNumber = input.nextInt();
                    System.out.println();
                    System.out.print("Enter the contact's email: ");
                    String eMail = input.next();
                    System.out.println();
                    System.out.print("Enter the contact's address: ");
                    String address = input.nextLine();
                    System.out.println();
                    System.out.print("Enter the contacts birthday: ");
                    String bDay = input.nextLine();
                    System.out.println();
                    System.out.print("Do you have any notes? Please enter if you do: ");
                    String note = input.nextLine();
                    if (phoneBook.contactExists(fName)) {
                        System.out.println("Contact already exist!");
                        break;
                    } else {
                        Contact c = new Contact(fName, lName, pNumber, address, address, bDay, note);
                        phoneBook.addContact(c);
                        System.out.println("Contact added successfully");
                    }
                    break;
                case 2:
                    System.out.println("Enter search criteria: ");
                    System.out.println("1- Name");
                    System.out.println("2- Phone number");
                    System.out.println("3- Email address");
                    System.out.println("4- Address");
                    System.out.println("5- Birthday");
                    System.out.print("Choose a criteria: ");
                    int criteria = input.nextInt();
                    if (criteria == 1) {
                        System.out.println();
                        System.out.print("Enter the contacts name: ");
                        String cName = input.nextLine();
                        phoneBook.findContact(cName);
                    } else if (criteria == 2) {
                        System.out.println();
                        System.out.print("Enter the contacts phone number: ");
                        int cNum = input.nextInt();
                        phoneBook.findContact(cNum);
                    } else if (criteria == 3) {
                        System.out.println();
                        System.out.print("Enter the contacts email address: ");
                        String cMail = input.nextLine();
                        phoneBook.searchContacts(cMail);
                    } else if (criteria == 4) {
                        System.out.println();
                        System.out.print("Enter the contacts address: ");
                        String cAddress = input.nextLine();
                        phoneBook.searchContacts(cAddress);
                    } else if (criteria == 5) {
                        System.out.println();
                        System.out.print("Enter the contacts birthday: ");
                        String cBday = input.nextLine();
                        phoneBook.searchContacts(cBday);
                    } else
                        System.out.println("Wrong input!");
                    break;
                case 3:
                    System.out.print("Enter the contacts name: ");
                    String cName = input.nextLine();
                    phoneBook.delete(cName);
                    break;
                case 4:
                    System.out.print("Enter the event's title: ");
                    String eTitle = input.nextLine();
                    System.out.println();
                    System.out.print("Enter contact name: ");
                    String eCont = input.nextLine();
                    Contact consContact = phoneBook.returnContactByName(eCont);
                    System.out.println();
                    System.out.print("Enter event date(DD/MM/YYYY): ");
                    String eTaree5 = input.nextLine();
                    System.out.println();
                    System.out.print("Enter event time(HH:MM): ");
                    String eTime = input.nextLine();
                    System.out.println();
                    System.out.print("Enter event location: ");
                    String eLocation = input.nextLine();
                    if (eventList.findConflict(eTaree5, eTime)) {
                        System.out.println("The event conflicts with an existing event!");
                    } else {
                        if (consContact == null) {
                            System.out.println("Contact does not exist!");
                            break;
                        }

                        Event e = new Event(eTitle, eTaree5, eLocation, eTime, consContact);
                        System.out.println("Event scheduled succesfully!");
                    }
                    break;
                case 5:
                    System.out.println("Enter search criteria: ");
                    System.out.println("1- Contact name");
                    System.out.println("2- Event title");
                    System.out.print("Please choose an option: ");
                    int eCriteria = input.nextInt();
                    if (eCriteria == 1) {
                        System.out.println();
                        System.out.print("Enter contact's name: ");
                        String printContactEvent = input.nextLine();
                        eventList.printAllContactEvent(printContactEvent);
                    } else if (eCriteria == 2) {
                        System.out.println();
                        System.out.print("Enter event title: ");
                        String eTitleSearch = input.nextLine();
                        eventList.printEvent(eTitleSearch);
                    } else
                        System.out.println("Wrong input!");
                    break;
                case 6:
                    System.out.print("Enter the first name: ");
                    String fNamePrint = input.nextLine();
                    phoneBook.printContactsFname(fNamePrint);
                    break;
           
            }

        } while (true);
    }

}