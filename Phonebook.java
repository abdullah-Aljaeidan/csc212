import java.util.InputMismatchException;
import java.util.Scanner;

// Phonebook class that contains all the methods to run the phonebook
public class Phonebook {

    // LinkedlistADT is a custom linked list class that we made for this project
    private LinkedListADT<Contact> phoneBook;
    private LinkedListADT<Event> eventList;
    // Scanner object to read user input
    private Scanner input;

    // Default constructor
    public Phonebook() {
        phoneBook = new LinkedListADT<>();
        eventList = new LinkedListADT<Event>();
        input = new Scanner(System.in);
    }

    // Pseudo-Main method (run method is called in main method)

    public void run() {
        // Option variable to store user input
        int option = 212;
        // Do-while loop to display menu and read user input
        do {
            // Display menu
            displayMenu();
            // Read user input
            try {
                option = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option!");
                input.nextLine();
                continue;
            }
            // Switch to call methods based on user input
            switch (option) {
                // Add a contact
                case 1:
                    addContact();
                    break;
                // Search for a contact
                case 2:
                    printContact();
                    break;
                // Delete a contact
                case 3:
                    deleteContact();
                    break;
                // Schedule an event
                case 4:
                    scheduleEvent();
                    break;
                // Print event details
                case 5:
                    printEventDetails();
                    break;
                // Print contacts by first name
                case 6:
                    printContactsByFirstName();
                    break;
                // Print all events in alphabetical order
                case 7:
                    printAllEvents();
                    break;
                // Exit
                case 8:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Wrong input! Please enter a valid option.");
            }

        } while (option != 8);
    }

    // Method to display menu for user
    private void displayMenu() {
        System.out.println("Hello! Welcome to your phonebook, please choose an option:");
        System.out.println("1- Add a contact");
        System.out.println("2- Search for a contact");
        System.out.println("3- Delete a contact");
        System.out.println("4- Schedule an event");
        System.out.println("5- Print event details");
        System.out.println("6- Print contacts by first name");
        System.out.println("7- Print all events in alphabetical order");
        System.out.println("8- Exit");
        System.out.print("Choose an option: ");
    }

    // Method to create a contact based on user input
    private Contact createContact() {
        System.out.print("Enter the contact's first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter the contact's last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter the contact's phone number (No hyphens or brackets!): ");
        String phoneNumber = input.nextLine();

        // Check if phone number is valid
        // Can't do a while loop because the position of possible user errors is unknown
        for (int i = 0; i < phoneNumber.length(); i++) {
            boolean isLetter = Character.isLetter(phoneNumber.charAt(i));
            boolean isSpace = Character.isSpaceChar(phoneNumber.charAt(i));
            boolean isSpecialChar = phoneNumber.charAt(i) == '(' || phoneNumber.charAt(i) == ')'
                    || phoneNumber.charAt(i) == '-';
            boolean lessOrMoreThan10Digits = phoneNumber.length() < 10 || phoneNumber.length() > 10;
            if (isLetter || isSpace || isSpecialChar || lessOrMoreThan10Digits) {
                System.out.println("Please enter a valid phone number!");
                return null;
            }
        }

        System.out.print("Enter the contact's email: ");
        String email = input.nextLine();

        // Check if email is valid
        // Can't do a while loop because the index of the @ symbol is unknown
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@')
                break;
            if (i == email.length() - 1) {
                System.out.println("Please enter a valid email address!");
                return null;
            }
        }

        System.out.print("Enter the contact's address: ");
        String address = input.nextLine();

        System.out.print("Enter the contact's birthday DD/MM/YYYY: ");
        String birthday = input.nextLine();

        // Check if birthday is valid
        boolean checkBirthday = true;
        do {
            if (birthday.length() != 10) {
                System.out.println("Please enter a valid birthday!");
                System.out.print("Enter the contact's birthday: ");
                birthday = input.nextLine();
                continue;
            } else if (birthday.charAt(2) != '/' || birthday.charAt(5) != '/') {
                System.out.println("Please enter a valid birthday!");
                System.out.print("Enter the contact's birthday: ");
                birthday = input.nextLine();
                continue;
            } else {
                checkBirthday = false;
            }

        } while (checkBirthday);

        System.out.print("Enter any notes for the contact: ");
        String notes = input.nextLine();

        return new Contact(firstName, lastName, phoneNumber, email, address, birthday, notes);
    }

    // Method to add a contact to the phonebook
    private void addContact() {
        Contact newContact = createContact();
        if (newContact == null) {
            System.out.println("Contact not created!");
            return;
        } else if (searchContact(newContact.getName()) != null) {
            System.out.println("Contact already exists!");
            return;
        } else {
            phoneBook.insert(newContact);
            System.out.println("Contact added successfully!");
        }
    }

    // Searches for a contact based on 5 different criteria
    private Contact searchContact(String search) {
        if (phoneBook.empty()) {
            return null;
        }
        phoneBook.findFirst();
        while (!phoneBook.last()) {
            // Boolean statements are initizalized every iteration. They are there to make
            // code easier to read
            boolean equalEmail = phoneBook.retrieve().getEmailAddress().equalsIgnoreCase(search);
            boolean equalBirthday = phoneBook.retrieve().getBirthday().equalsIgnoreCase(search);
            boolean equalAddress = phoneBook.retrieve().getAdress().equalsIgnoreCase(search);
            boolean equalNumber = phoneBook.retrieve().getPhoneNumber().equalsIgnoreCase(search);
            boolean equalName = phoneBook.retrieve().getName().equalsIgnoreCase(search);
            if (equalAddress || equalBirthday || equalEmail || equalNumber || equalName)
                return phoneBook.retrieve();
            phoneBook.findNext();
        }
        // Code below is to check the last contact in the list
        boolean equalEmail = phoneBook.retrieve().getEmailAddress().equalsIgnoreCase(search);
        boolean equalBirthday = phoneBook.retrieve().getBirthday().equalsIgnoreCase(search);
        boolean equalAddress = phoneBook.retrieve().getAdress().equalsIgnoreCase(search);
        boolean equalNumber = phoneBook.retrieve().getPhoneNumber().equalsIgnoreCase(search);
        boolean equalName = phoneBook.retrieve().getName().equalsIgnoreCase(search);
        if (equalAddress || equalBirthday || equalEmail || equalNumber || equalName)
            return phoneBook.retrieve();
        return null;
    }

    // Method to search for a contact based on user input
    private void printContact() {
        Contact contact;
        System.out.println("1- Name");
        System.out.println("2- Phone number");
        System.out.println("3- Email address");
        System.out.println("4- Address");
        System.out.println("5- Birthday");
        System.out.print("Choose a criteria: ");
        int criteria = input.nextInt();
        input.nextLine();
        // Switch to call methods based on user input
        switch (criteria) {
            // Search by name
            case 1:
                System.out.print("Enter the contact's name: ");
                String contactName = input.nextLine();
                contact = searchContact(contactName);
                if (contact != null) {
                    System.out.println("Contact found!");
                    System.out.println(contact.toString());
                } else
                    System.out.println("Contact does not exist!");

                break;
            // Search by number
            case 2:
                System.out.print("Enter the contact's phone number: ");
                String phoneNumber = input.nextLine();
                contact = searchContact(phoneNumber);
                if (contact != null) {
                    System.out.println("Contact found!");
                    System.out.println(contact.toString());
                } else
                    System.out.println("Contact does not exist!");
                break;
            // Search by email
            case 3:
                System.out.print("Enter the contact's email address: ");
                String email = input.nextLine();
                contact = searchContact(email);
                if (contact != null) {
                    System.out.println("Contact found!");
                    System.out.println(contact.toString());
                } else
                    System.out.println("Contact does not exist!");
                break;
            // Search by address
            case 4:
                System.out.print("Enter the contact's address: ");
                String address = input.nextLine();
                contact = searchContact(address);
                if (contact != null) {
                    System.out.println("Contact found!");
                    System.out.println(contact.toString());
                } else
                    System.out.println("Contact does not exist!");
                break;
            // Search by birthday
            case 5:
                System.out.print("Enter the contact's birthday: ");
                String birthday = input.nextLine();
                contact = searchContact(birthday);
                if (contact != null) {
                    System.out.println("Contact found!");
                    System.out.println(contact.toString());
                } else
                    System.out.println("Contact does not exist!");
                break;
            default:
                System.out.println("Wrong input! Please enter a valid option.");
        }
    }

    // Method to delete a contact
    private void deleteContact() {
        System.out.print("Enter the contact's name: ");
        String contactName = input.nextLine();
        Contact contact = searchContact(contactName);
        if (contact == null) {
            System.out.println("Contact does not exist!");
            return;
        } else {
            // Delete all events associated with the contact
            eventList.findFirst();
            if (!eventList.empty()) {
                while (!eventList.last()) {
                    if (eventList.retrieve().getContactName().equalsIgnoreCase(contactName))
                        eventList.remove();
                    eventList.findNext();
                }
                // Code below is to check the last event in the list
                if (eventList.retrieve().getContactName().equalsIgnoreCase(contactName))
                    eventList.remove();
            }
            // Delete the contact
            phoneBook.remove();
            System.out.println("Contact deleted successfully!");
        }
    }

    // Method to print all contacts with the same first name
    private void printContactsByFirstName() {
        System.out.print("Enter the first name: ");
        String firstName = input.nextLine();
        if (phoneBook.empty()) {
            System.out.println("Phonebook is empty!");
            return;
        }
        phoneBook.findFirst();
        // Count prints is used to check if any contacts were printed
        int countPrints = 0;
        while (!phoneBook.last()) {
            if (phoneBook.retrieve().getFirstName().equalsIgnoreCase(firstName)) {
                System.out.println(phoneBook.retrieve().toString());
                countPrints++;
            }
            phoneBook.findNext();
        }
        // Code below is to check the last contact in the list
        if (phoneBook.retrieve().getFirstName().equalsIgnoreCase(firstName)) {
            System.out.println(phoneBook.retrieve().toString());
            countPrints++;
        }
        // If no contacts were printed, print a message
        if (countPrints == 0)
            System.out.println("No contacts found with that name!");
    }

    // Method to check if an event already exists at a certain date and time
    private boolean findEventConflicts(String date, String time) {
        if (eventList.empty())
            return false;
        eventList.findFirst();
        while (!eventList.last()) {
            // Boolean statements are initizalized every iteration. They are there to make
            // code easier to read
            boolean equalDate = eventList.retrieve().getDate().equalsIgnoreCase(date);
            boolean equalTime = eventList.retrieve().getTime().equalsIgnoreCase(time);
            if (equalDate && equalTime)
                return true;
            eventList.findNext();
        }
        // Code below is to check the last event in the list
        boolean equalDate = eventList.retrieve().getDate().equalsIgnoreCase(date);
        boolean equalTime = eventList.retrieve().getTime().equalsIgnoreCase(time);
        if (equalDate && equalTime)
            return true;
        return false;
    }

    // Method to schedule an event
    private void scheduleEvent() {
        System.out.print("Enter the event's title: ");
        String eventTitle = input.nextLine();
        System.out.print("Enter contact name: ");
        String contactName = input.nextLine();
        // Check if contact exists
        Contact contact = searchContact(contactName);

        // If contact does not exist, print a message and return
        if (contact == null) {
            System.out.println("Contact does not exist!");
            return;
        } else {
            System.out.print("Enter event date (DD/MM/YYYY): ");
            boolean dateValidation = true;
            String eventDate = "D. Mohammed Aldalhan";
            do {
                eventDate = input.nextLine();
                if (eventDate.length() != 10) {
                    System.out.println("Please enter a valid date!");
                    System.out.print("Enter event date (DD/MM/YYYY): ");
                    continue;
                } else if (eventDate.charAt(2) != '/' || eventDate.charAt(5) != '/') {
                    System.out.println("Please enter a valid date!");
                    System.out.print("Enter event date (DD/MM/YYYY): ");
                    continue;
                } else {
                    dateValidation = false;
                }

            } while (dateValidation);
            System.out.print("Enter event time (HH:MM 24-Hour): ");
            String eventTime = "More boiler plate code (Yay java!)";
            boolean timeValidation = true;
            do {
                eventTime = input.nextLine();
                if (eventTime.length() != 5) {
                    System.out.println("Please enter a valid time!");
                    System.out.print("Enter event time (HH:MM 24-Hour): ");
                    continue;
                } else if (eventTime.charAt(2) != ':') {
                    System.out.println("Please enter a valid time!");
                    System.out.print("Enter event time (HH:MM 24-Hour): ");
                    continue;
                } else {
                    timeValidation = false;
                }

            } while (timeValidation);
            // Check if event already exists at the same date and time
            if (findEventConflicts(eventDate, eventTime)) {
                System.out.println("The event conflicts with an existing event!");
                return;
            }
            // if no conflicts, create the event and insert it into the list
            System.out.print("Enter event location: ");
            String eventLocation = input.nextLine();
            Event event = new Event(eventTitle, eventDate, eventLocation, eventTime, contactName);

            eventList.insert(event);
            System.out.println("Event scheduled successfully!");

        }
    }

    // Method to print all events associated with a contact
    private void printAllContactEvents(String name) {
        if (eventList.empty()) {
            System.out.println("No events scheduled!");
            return;
        }
        eventList.findFirst();
        // Count prints is used to check if any events were printed
        int countPrints = 0;
        while (!eventList.last()) {
            if (eventList.retrieve().getContactName().equalsIgnoreCase(name)) {
                System.out.println(eventList.retrieve().toString());
                countPrints++;
            }
            eventList.findNext();
        }
        // Code below is to check the last event in the list
        if (eventList.retrieve().getContactName().equalsIgnoreCase(name)) {
            System.out.println(eventList.retrieve().toString());
            countPrints++;
        }
        // If no events were printed, print a message
        if (countPrints == 0)
            System.out.println("No events found!");

    }

    // Method to print an event based on its title
    private void printEvent(String eventTitle) {
        if (eventList.empty()) {
            System.out.println("No events scheduled!");
            return;
        }
        eventList.findFirst();
        while (!eventList.last()) {
            if (eventList.retrieve().getTitle().equalsIgnoreCase(eventTitle)) {
                System.out.println(eventList.retrieve().toString());
                return;
            }
            eventList.findNext();
        }
        // Code below is to check the last event in the list
        if (eventList.retrieve().getTitle().equalsIgnoreCase(eventTitle))
            System.out.println(eventList.retrieve().toString());

    }

    // Method to print event details based on user input
    private void printEventDetails() {
        System.out.println("Enter search criteria:");
        System.out.println("1- Contact name");
        System.out.println("2- Event title");
        System.out.print("Please choose an option: ");
        int criteria = input.nextInt();
        input.nextLine();

        // Switch to call methods based on user input
        switch (criteria) {
            // Print all events associated with a contact
            case 1:
                System.out.print("Enter contact's name: ");
                String contactName = input.nextLine();
                printAllContactEvents(contactName);
                break;
            // Print an event based on its title
            case 2:
                System.out.print("Enter event title: ");
                String eventTitle = input.nextLine();
                printEvent(eventTitle);
                break;
            default:
                System.out.println("Wrong input! Please enter a valid option.");
        }
    }

    // Method to print all events in alphabetical order
    private void printAllEvents() {
        if (eventList.empty()) {
            System.out.println("No events scheduled!");
            return;
        }
        eventList.findFirst();

        while (!eventList.last()) {
            System.out.println(eventList.retrieve().toString());
            eventList.findNext();
        }
        // Code below is to print the last event in the list
        System.out.println(eventList.retrieve().toString());

    }

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.run();
    }
}