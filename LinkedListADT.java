public class LinkedListADT<T extends Comparable<T>> {

    private Node<T> head;
    private Node<T> current;

    // Default constructor
    public LinkedListADT() {
        head = current = null;
    }

    // Checks if list is empty
    public boolean empty() {
        return head == null;
    }

    // Checks if current is at the end of the list
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

    // Returns the current node
    public T retrieve() {
        return current.getData();
    }

    // Updates the current node's data
    public void update(T val) {
        current.setData(val);
    }

    // Inserts a new node (in order)
    public void insert(T val) {
        Node<T> tmp = new Node<T>(val);
        if (empty()) {
            current = head = tmp;
            return;
        } else if (val.compareTo(head.getData()) < 0) {
            tmp.setNext(head);
            head = tmp;
            current = head;
            return;
        } else {
            findFirst();
            while (current.getNext() != null) {
                if (val.compareTo(current.getNext().getData()) > 0) {
                    tmp.setNext(current.getNext());
                    current.setNext(tmp);
                    current = tmp;
                }
            }
            if (current.getNext() == null) {
                tmp.setNext(current.getNext());
                current.setNext(tmp);
                current = tmp;
            } else {
                findNext();
            }

        }
    }

    // Deletes the current node
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
