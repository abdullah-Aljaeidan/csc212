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

    public void searchContacts(String emailAdressBirthday) {
        if (empty()) {
            System.out.println("There are no contacts in the list");
            return;
        }
        findFirst();
        while (current != null) {
            boolean checkBirthday = current.getData().getBirthday().equals(emailAdressBirthday);
            boolean checkEmail = current.getData().getEmailAddress().equals(emailAdressBirthday);
            boolean checkAdress = current.getData().getAdress().equals(emailAdressBirthday);
            if (checkAdress || checkBirthday || checkEmail) {
                System.out.println(current.getData().toString());
            }
        }
    }

    public void findContact(int phoneNumber) {
        if (empty()) {
            System.out.println("List is empty");
            return;
        }
        findFirst();
        while (current != null) {
            if (current.getData().getPhoneNumber() == phoneNumber) {
                System.out.println(current.getData().toString());
                return;
            }
        }
    }

    public void findContact(String nameString) {
        if (empty()) {
            System.out.println("List is empty");
            return;
        }
        findFirst();
        while (current != null) {
            if (current.getData().getName().equalsIgnoreCase(nameString)) {
                System.out.println(current.getData().toString());
                return;
            }
        }

    }

    public void printFirstName() {
        if (empty()) {
            System.out.println("No contacts in list!");
        }
        findFirst();
        while (current != null) {
            System.out.println(current.getData().getFirstName());
        }
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

       public boolean contactExists(String checkName) {
        if (empty())
            return false;
        while (current != null) {
            if (current.getData().getName().equalsIgnoreCase(checkName))
                return true;
            findNext();
        }
        return false;
    }
    
    
    // Adds contact to the list with respect to list ordering
    public boolean addContact(Contact c) {
        Node tmp = new Node(c);
        if (empty()) {
            current = head = tmp;
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

    public void delete() {
        if (current == head) {
            head = head.getNext();
        } else {
            Node tmp = head;

            while (tmp.getNext() != current) {
                tmp = tmp.getNext();
            }

            tmp.setNext(current.getNext());
        }

        if (current.getNext() == null)
            current = head;
        else
            current = current.getNext();
    }
}
