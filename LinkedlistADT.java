public class LinkedlistADT {

    private Node head;
    private Node current;

    // Default constructor
    public LinkedlistADT() {
        head = current = null;
    }

    // Checks if list is empty
    public boolean empty() {
        return head == null;
    }

    // Sets current to head (First node)
    public void findFirst() {
        current = head;
    }

    // Sets current to next node
    public void findNext() {
        current = current.getNext();
    }

    public Contact[] searchContacts(String emailAdressBirthday) {
        if (empty())
            return null;
            //To be changed to linked list..eventually 
        Contact[] arrayContacts = new Contact[100];
        int countArrayContacts = 0;
        findFirst();
        while (current != null) {
            boolean checkBirthday = current.getData().getBirthday().equals(emailAdressBirthday);
            boolean checkEmail = current.getData().getEmailAddress().equals(emailAdressBirthday);
            boolean checkAdress = current.getData().getAdress().equals(emailAdressBirthday);
            if (checkAdress || checkBirthday || checkEmail) {
                arrayContacts[countArrayContacts++] = current.getData();
            }
        }
        return arrayContacts;

    }

    // Method to check if contact is already in list
    public boolean contactIsRepeated(Contact c) {
        if (empty())
            return false;
        while (current != null) {
            // Boolean variables made to make code easier to read, placed inside loop to be
            // initialized every iteration
            boolean nameEqual = current.getData().getName().equals(c.getName());
            boolean numberEqual = current.getData().getPhoneNumber() == (c.getPhoneNumber());
            if (nameEqual || numberEqual)
                return true;

            findNext();
        }
        return false;
    }

    // Adds contact to the list with respect to list ordering
    public boolean addContact(Contact c) {
        Node tmp = new Node(c);
        if (empty()) {
            head = tmp;
            return true;
        } else if (contactIsRepeated(c)) {
            System.out.println("Contact already in list");
            return false;
        } else if (c.compareTo(head.getData()) < 0) {
            tmp.setNext(head);
            head = tmp;
            return true;
        } else {
            while (current.getNext() != null) {
                if (c.compareTo(current.getNext().getData()) < 0) {
                    tmp.setNext(current);
                    return true;
                }
                findNext();
            }
        }
        return false;
    }
}
