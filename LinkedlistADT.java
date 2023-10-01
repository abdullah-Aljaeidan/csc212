public class LinkedlistADT<T extends Comparable<T>> {

    private Node<T> head;
    private Node<T> current;

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
        if (current.getData() instanceof Contact) {
            findFirst();
            while (current != null) {
                boolean checkBirthday = ((Contact) current.getData()).getBirthday().equals(emailAdressBirthday);
                boolean checkEmail = ((Contact) current.getData()).getEmailAddress().equals(emailAdressBirthday);
                boolean checkAdress = ((Contact) current.getData()).getAdress().equals(emailAdressBirthday);
                if (checkAdress || checkBirthday || checkEmail) {
                    System.out.println(current.getData().toString());
                    System.out.println();
                }
            }
        } else {
            System.out.println("Wrong input!");
            return;
        }
    }

    public void findContactByNumber(int phoneNumber) {
        if (empty()) {
            System.out.println("List is empty");
            return;
        }
        if (current.getData() instanceof Contact) {
            findFirst();
            while (current != null) {
                if (((Contact) current.getData()).getPhoneNumber() == phoneNumber) {
                    System.out.println("Contact found!");
                    System.out.println(current.getData().toString());
                    return;
                }
            }
            System.out.println("Contact not found!");
        } else {
            System.out.println("Wrong input!");
            return;
        }
    }

    public void findContactByName(String nameString) {
        if (empty()) {
            System.out.println("List is empty");
            return;
        }else{
        findFirst();
        if (current.getData() instanceof Contact) {
            
            while (current != null) {
                if (((Contact) current.getData()).getName().equalsIgnoreCase(nameString)) {
                    System.out.println("Contact found!");
                    System.out.println(current.getData().toString());
                    return;
                }
                current=current.getNext();
            }
            System.out.println("Contact not found!");
        }
     } 

    }

    // Method to check if contact is already in list
    public boolean contactIsRepeated(T c) {
        if (empty())
            return false;
        if (c instanceof Contact) {
            while (current != null) {
                // Boolean variables made to make code easier to read, placed inside loop to be
                // initialized every iteration
                boolean nameEqual = ((Contact) current.getData()).getName().equals(((Contact) c).getName());
                boolean numberEqual = ((Contact) current.getData())
                        .getPhoneNumber() == (((Contact) c).getPhoneNumber());
                if (nameEqual || numberEqual)
                    return true;

                findNext();
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean contactExists(String checkName) {
        if (empty())
            return false;
        if (current.getData() instanceof Contact) {
            while (current != null) {
                if (((Contact) current.getData()).getName().equalsIgnoreCase(checkName))
                    return true;
                findNext();
            }
            return false;
        } else {
            return false;
        }
    }

    // Adds contact to the list with respect to list ordering
    public boolean add(T cE) {
        // cE= Contact or Event
        Node<T> tmp = new Node(cE);
        if (cE instanceof Contact) {
            if (empty()) {
                current = head = tmp;
                return true;

            } else if (contactIsRepeated(cE)) {
                System.out.println("Contact already in list");
                return false;

            } else if (cE.compareTo(head.getData()) < 0) {
                tmp.setNext(head);
                head = tmp;
                current = tmp;
                return true;

            } else {
                while (current != null) {
                    if (cE.compareTo(current.getNext().getData()) < 0) {
                        tmp.setNext(current);
                        current = tmp;
                        return true;
                    }
                    findNext();
                }
            }
        } else {
            if (empty()) {
                current = head = tmp;
                return true;

            } else if (((Event) cE).compareTo((Event) head.getData()) < 0) {
                tmp.setNext(head);
                head = tmp;
                return true;

            } else {
                findFirst(); // current=head;

                while (current.getNext() != null) {
                    if (((Event) cE).compareTo((Event) current.getData()) < 0) {
                        tmp.setNext(current.getNext());
                        current.setNext(tmp);
                        current = tmp;
                        return true;

                    }
                    findNext();
                }
                current.setNext(tmp);
                current = tmp;
                return true;
            }

        }
        return false;
    }

    public void delete(String name) {
        if (!contactExists(name)) {
            System.out.println("Contact does not exist!");
            return;
        }
        if (current == head) {
            head = head.getNext();
        } else {
            Node<T> tmp = head;

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

    public Contact returnContactByName(String name) {
        if (empty())
            return null;
        else{
          findFirst();
            if (current.getData() instanceof Contact) {
            
            while (current != null) {
                if (((Contact) current.getData()).getName().equalsIgnoreCase(name))
                    return ((Contact) current.getData());
                findNext();
            }
            return null;
        }
        return null;
    }
}

    public void printContactsFname(String fName) {
        if (empty()) {
            System.out.println("No contacts in phonebook!");
            return;
        }
        if (current.getData() instanceof Contact) {
            findFirst();
            int countPrints = 0;
            while (current != null) {
                if (((Contact) current.getData()).getFirstName().equalsIgnoreCase(fName)) {
                    System.out.println(current.getData().toString());
                    System.out.println();
                    countPrints++;
                }
                findNext();
            }
            if (countPrints == 0)
                System.out.println("No contacts by that name found!");
            else
                System.out.println("Contact(s) found!");
        } else {
            System.out.println("Wrong input!");
            return;
        }
    }

    public boolean findConflict(String date, String time) {
        if(empty())
           return false;
           else{
        findFirst();
        while (current != null) {
            boolean checkDate = ((Event) (current.getData())).getDate().equalsIgnoreCase(date);
            boolean checkTime = ((Event) (current.getData())).getTime().equalsIgnoreCase(time);
            if (checkDate || checkTime) {
                return true;
            }
            findNext();
        }
    }
        return false;
    }

    public void printAllContactEvents(String name) {
        if (empty()) {
            System.out.println("No events scheduled!");
            return;
        }
        findFirst();
        int countPrints = 0;
        while (current != null) {
            if (((Event) current.getData()).getEventParticipant().getName().equalsIgnoreCase(name)) {
                System.out.println(current.getData().toString());
                System.out.println();
                countPrints++;
            }

        }
        if (countPrints == 0)
            System.out.println("No events found!");
        else
            System.out.println("Event(s) found!");
    }

    public void printEvent(String eventTitle) {
        if (empty()) {
            System.out.println("No events scheduled!");
            return;
        }
        if (current.getData() instanceof Event) {
            findFirst();
            while (current != null) {
                if (((Event) current.getData()).getTitle().equalsIgnoreCase(eventTitle)) {
                    System.out.println(current.getData().toString());
                    return;
                }
                findNext();
            }
            System.out.println("No event found!");
        }

    }

    public void printAllEvents() {
        if (empty()) {
            System.out.println("No events in phonebook!");
            return;
        }
        if (current.getData() instanceof Event) {
            System.out.println("Events found!");
            findFirst();
            while (current != null) {
                System.out.println(current.getData().toString());
            }
        } else {
            System.out.println("Wrong input!");
            return;
        }
    }

    /*
     * public void EventAdapter(String name){
     * Contact con =returnContactbyName(name);
     * if(con==null)
     * System.out.println("");
     * 
     * }
     * 
     * 
     * 
     * /*
     * title="lunch"
     * date="2022/2/2"
     * loc="riyadh"....
     * time="10:30"
     * contact name= "aziz"
     * if(contactExists(aziz) && findConflict(String date, String time)){
     * Contact newC = new Contact(returnContactbyname(aziz)) // we dont have to
     * worry wethere if returncontactbyname returns null because contactsExists will
     * return false
     * Event E1 = new Event(title,date,loc,newC);
     * addAllevents(E1);
     * 
     * 
     * 
     * }
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

}
