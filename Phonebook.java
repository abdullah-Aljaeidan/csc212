import java.util.Scanner;

public class Phonebook<T> {
    private LinkedlistADT<Contact> phoneBook;
    private LinkedlistADT<Event> eventList;
    private Scanner input;

    public Phonebook() {
        phoneBook = new LinkedlistADT<>();
        eventList = new LinkedlistADT<Event>();
        input = new Scanner(System.in);
    }

    public void run() {
        int option;
        do {
            displayMenu();
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    addContact();
                    break;
                case 2:
                    searchContact();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    scheduleEvent();
                    break;
                case 5:
                    printEventDetails();
                    break;
                case 6:
                    printContactsByFirstName();
                    break;
                case 7:
                    printAllEvents();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Wrong input! Please enter a valid option.");
            }

        } while (true);
    }

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

    private Contact createContact() {
        System.out.print("Enter the contact's first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter the contact's last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter the contact's phone number: ");
        int phoneNumber = input.nextInt();
        input.nextLine(); // Consume the newline character
        System.out.print("Enter the contact's email: ");
        String email = input.nextLine();
        System.out.print("Enter the contact's address: ");
        String address = input.nextLine();
        System.out.print("Enter the contact's birthday: ");
        String birthday = input.nextLine();
        System.out.print("Enter any notes for the contact: ");
        String notes = input.nextLine();

        return new Contact(firstName, lastName, phoneNumber, email, address, birthday, notes);
    }

    public boolean contactIsRepeatedOrExists(Contact c) {
        phoneBook.findFirst();
        while (phoneBook.retrieve() != null) {
            boolean equalNumber = c.getPhoneNumber() == phoneBook.retrieve().getPhoneNumber();
            boolean equalName = c.getName().equalsIgnoreCase(phoneBook.retrieve().getName());
            if (equalNumber || equalName)
                return true;
            phoneBook.findNext();
        }
        return false;
    }

    private void addContact() {
        Contact newContact = createContact();
        if (contactIsRepeatedOrExists(newContact)) {
            System.out.println("Contact already exists!");
            return;
        } else {
            phoneBook.insert(newContact);
            System.out.println("Contact added successfully!");
        }
    }

    private Contact searchContactByName(String name) {
        phoneBook.findFirst();
        while (phoneBook.retrieve() != null) {
            if (phoneBook.retrieve().getName().equalsIgnoreCase(name))
                return phoneBook.retrieve();
            phoneBook.findNext();
        }
        return null;
    }

    private Contact searchContactByNumber(int number) {
        phoneBook.findFirst();
        while (phoneBook.retrieve() != null) {
            if (phoneBook.retrieve().getPhoneNumber() == number)
                return phoneBook.retrieve();
            phoneBook.findNext();
        }
        return null;
    }

    private Contact searchContactByEmailBirthdayAddress(String emailBirthdayAdress) {
        phoneBook.findFirst();
        while (phoneBook.retrieve() != null) {
            // Boolean statements are initizalized every iteration to check if the input is
            // equal to the contact's email, birthday or address and to make code easier to
            // read
            boolean equalEmail = phoneBook.retrieve().getEmailAddress().equalsIgnoreCase(emailBirthdayAdress);
            boolean equalBirthday = phoneBook.retrieve().getBirthday().equalsIgnoreCase(emailBirthdayAdress);
            boolean equalAddress = phoneBook.retrieve().getAdress().equalsIgnoreCase(emailBirthdayAdress);
            if (equalAddress || equalBirthday || equalEmail)
                return phoneBook.retrieve();
            phoneBook.findNext();
        }
        return null;
    }

    private void searchContact() {
        String emailBirthdayAdress;
        Contact contact;
        System.out.println("Enter search criteria:");
        System.out.println("1- Name");
        System.out.println("2- Phone number");
        System.out.println("3- Email address");
        System.out.println("4- Address");
        System.out.println("5- Birthday");
        System.out.print("Choose a criteria: ");
        int criteria = input.nextInt();
        input.nextLine();

        switch (criteria) {
            case 1:
                System.out.print("Enter the contact's name: ");
                String contactName = input.nextLine();
                contact = searchContactByName(contactName);
                if (contact != null) {
                    System.out.println("Contact found!");
                    System.out.println(contact.toString());
                } else
                    System.out.println("Contact does not exist!");

                break;
            case 2:
                System.out.print("Enter the contact's phone number: ");
                int phoneNumber = input.nextInt();
                input.nextLine();
                contact = searchContactByNumber(phoneNumber);
                if (contact != null) {
                    System.out.println("Contact found!");
                    System.out.println(contact.toString());
                } else
                    System.out.println("Contact does not exist!");
                break;
            case 3:
                System.out.println("Enter the contact's email address");
                emailBirthdayAdress = input.nextLine();
                contact = searchContactByEmailBirthdayAddress(emailBirthdayAdress);
                if (contact != null) {
                    System.out.println("Contact found!");
                    System.out.println(contact.toString());
                } else
                    System.out.println("Contact does not exist!");
                break;
            case 4:
                System.out.println("Enter the contact's address");
                emailBirthdayAdress = input.nextLine();
                contact = searchContactByEmailBirthdayAddress(emailBirthdayAdress);
                if (contact != null) {
                    System.out.println("Contact found!");
                    System.out.println(contact.toString());
                } else
                    System.out.println("Contact does not exist!");
                break;
            case 5:
                System.out.println("Enter the contact's birthday");
                emailBirthdayAdress = input.nextLine();
                contact = searchContactByEmailBirthdayAddress(emailBirthdayAdress);
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

    private void deleteContact() {
        System.out.print("Enter the contact's name: ");
        String contactName = input.nextLine();
        Contact contact = searchContactByName(contactName);
        if (contact == null) {
            System.out.println("Contact does not exist!");
            return;
        } else {
            phoneBook.remove();
            System.out.println("Contact deleted successfully!");
        }
    }

    private void printContactsByFirstName() {
        System.out.print("Enter the first name: ");
        String firstName = input.nextLine();
        if (phoneBook.empty()) {
            System.out.println("No contacts found!");
            return;
        }
        phoneBook.findFirst();
        int countPrints = 0;
        while (phoneBook.retrieve() != null) {
            if (phoneBook.retrieve().getFirstName().equalsIgnoreCase(firstName)) {
                System.out.println(phoneBook.retrieve().toString());
                countPrints++;
            }
            phoneBook.findNext();
        }
        if (countPrints == 0)
            System.out.println("No contacts found!");
    }

    public boolean findEventConflicts(String date, String time) {
        eventList.findFirst();
        while (eventList.retrieve() != null) {
            boolean equalDate = eventList.retrieve().getDate().equalsIgnoreCase(date);
            boolean equalTime = eventList.retrieve().getTime().equalsIgnoreCase(time);
            if (equalDate && equalTime)
                return true;
            eventList.findNext();
        }
        return false;
    }

    private void scheduleEvent() {
        System.out.print("Enter the event's title: ");
        String eventTitle = input.nextLine();
        System.out.print("Enter contact name: ");
        String contactName = input.nextLine();
        Contact contact = searchContactByName(contactName);

        if (contact == null) {
            System.out.println("Contact does not exist!");
            return;
        } else {
            System.out.print("Enter event date (DD/MM/YYYY): ");
            String eventTaree5 = input.nextLine();
            System.out.print("Enter event time (HH:MM): ");
            String eventTime = input.nextLine();
            if (findEventConflicts(eventTaree5, eventTime)) {
                System.out.println("The event conflicts with an existing event!");
                return;
            }
            System.out.print("Enter event location: ");
            String eventLocation = input.nextLine();
            Event event = new Event(eventTitle, eventTaree5, eventLocation, eventTime, contact);

            eventList.insert(event);
            System.out.println("Event scheduled successfully!");

        }
    }

    private void printAllContactEvents(String name) {
        if (eventList.empty()) {
            System.out.println("No events scheduled!");
            return;
        }
        eventList.findFirst();
        int countPrints = 0;
        while (eventList.retrieve() != null) {
            if (eventList.retrieve().getEventParticipant().getName().equalsIgnoreCase(name)) {
                System.out.println(eventList.retrieve().toString());
                countPrints++;
            }
            eventList.findNext();
        }
        if (countPrints == 0)
            System.out.println("No events found!");
    }

    private void printEvent(String eventTitle) {
        if (eventList.empty()) {
            System.out.println("No events scheduled!");
            return;
        }
        eventList.findFirst();
        while (eventList.retrieve() != null) {
            if (eventList.retrieve().getTitle().equalsIgnoreCase(eventTitle)) {
                System.out.println(eventList.retrieve().toString());
                return;
            }
            eventList.findNext();
        }
        System.out.println("Event not found!");
    }

    private void printEventDetails() {
        System.out.println("Enter search criteria:");
        System.out.println("1- Contact name");
        System.out.println("2- Event title");
        System.out.print("Please choose an option: ");
        int criteria = input.nextInt();
        input.nextLine();

        switch (criteria) {
            case 1:
                System.out.print("Enter contact's name: ");
                String contactName = input.nextLine();
                printAllContactEvents(contactName);
                break;
            case 2:
                System.out.print("Enter event title: ");
                String eventTitle = input.nextLine();
                printEvent(eventTitle);
                break;
            default:
                System.out.println("Wrong input! Please enter a valid option.");
        }
    }

    private void printAllEvents() {
        if (eventList.empty()) {
            System.out.println("No events scheduled!");
            return;
        }
        eventList.findFirst();
        while (eventList.retrieve() != null) {
            System.out.println(eventList.retrieve().toString());
            eventList.findNext();
        }
    }

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook<>();
        phonebook.run();
    }
}