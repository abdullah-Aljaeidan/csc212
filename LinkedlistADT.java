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

    public boolean last() {
        return current.getNext() == null;
    }

    // Sets current to head (First node)
    public void findFirst() {
        current = head;
    }

    // Sets current to next node
    public void findNext() {
        current = current.getNext();
    }

    public T retrieve() {
        return current.getData();
    }

    public void update(T val) {
        current.setData(val);
    }

    public void insert(T val) {
        Node<T> tmp = new Node<T>(val);
        if (empty()) {
            current = head = tmp;
            return;
        } else if (val.compareTo(head.getData()) < 0) {
            tmp = head;
            head = new Node<T>(val);
            head.setNext(tmp);
            return;
        } else {
            //current or current.getNext()???
            while (current.getNext() !=  null) {
                if (val.compareTo(current.getNext().getData()) < 0) {
                    tmp.setNext(current);
                    current = tmp;
                    return;
                }
            }
            findNext();
        }
    }

    public void remove() {
        if (current == head) {
            head = head.getNext();
        } else {
            Node<T> tmp = head;

            while (tmp.getNext() != current)
                tmp = tmp.getNext();

            tmp.setNext(current.getNext());
        }

        if (current.getNext() == null)
            current = head;
        else
            current = current.getNext();
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
