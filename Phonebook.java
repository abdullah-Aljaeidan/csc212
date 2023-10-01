import java.util.Scanner;

public class Phonebook {
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
                    eventList.printAllEvents();
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

    private void addContact() {
        Contact newContact = createContact();
        if (phoneBook.contactExists(newContact.getName())) {
            System.out.println("Contact already exists!");
        } else {
            phoneBook.add(newContact);
            System.out.println("Contact added successfully");
        }
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

    private void searchContact() {
        String emailBirthdayAdress;
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
                phoneBook.findContactByName(contactName);
                break;
            case 2:
                System.out.print("Enter the contact's phone number: ");
                int phoneNumber = input.nextInt();
                input.nextLine();
                phoneBook.findContactByNumber(phoneNumber);
                break;
            case 3:
                System.out.println("Enter the contact's email address");
                emailBirthdayAdress = input.nextLine();
                phoneBook.searchContacts(emailBirthdayAdress);
                break;
            case 4:
                System.out.println("Enter the contact's address");
                emailBirthdayAdress = input.nextLine();
                phoneBook.searchContacts(emailBirthdayAdress);
                break;
            case 5:
                System.out.println("Enter the contact's birthday");
                emailBirthdayAdress = input.nextLine();
                phoneBook.searchContacts(emailBirthdayAdress);
                break;
            default:
                System.out.println("Wrong input! Please enter a valid option.");
        }
    }

    private void deleteContact() {
        System.out.print("Enter the contact's name: ");
        String contactName = input.nextLine();
        phoneBook.delete(contactName);
    }

    private void scheduleEvent() {
        System.out.print("Enter the event's title: ");
        String eventTitle = input.nextLine();
        System.out.print("Enter contact name: ");
        String contactName = input.nextLine();
        Contact contact = phoneBook.returnContactByName(contactName);

        if (contact == null) {
            System.out.println("Contact does not exist!");
            return;
        } else {
            System.out.print("Enter event date (DD/MM/YYYY): ");
            String eventTaree5 = input.nextLine();
            System.out.print("Enter event time (HH:MM): ");
            String eventTime = input.nextLine();

            if (eventList.findConflict(eventTaree5, eventTime)) {
                System.out.println("The event conflicts with an existing event!");
                return;
            }
            
            System.out.print("Enter event location: ");
            String eventLocation = input.nextLine();

            Event event = new Event(eventTitle, eventTaree5, eventLocation, eventTime, contact);
            eventList.add(event);
            System.out.println("Event scheduled successfully!");
        }
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
                eventList.printAllContactEvents(contactName);
                break;
            case 2:
                System.out.print("Enter event title: ");
                String eventTitle = input.nextLine();
                eventList.printEvent(eventTitle);
                break;
            default:
                System.out.println("Wrong input! Please enter a valid option.");
        }
    }

    private void printContactsByFirstName() {
        System.out.print("Enter the first name: ");
        String firstName = input.nextLine();
        phoneBook.printContactsFname(firstName);
    }

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.run();
    }
}