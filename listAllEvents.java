
public class listAllEvents {

    nodeLE head;
    nodeLE current;
    nodeLE Cnode;

    listAllEvents() {
        head = current = null;
    }

    public boolean Before(Object eObject, Object cObject) {
        // Cnode=(nodeLE)cObject;
        // nodeLE enode=(nodeLE)eObject;
        Event Event_e = (Event) eObject;
        Event Event_c = (Event) cObject;

        // Cnode.setData(Event_c);

        String cTitle = Event_c.title;
        String eTitle = Event_e.title;

        if (cTitle.compareTo(eTitle) < 0)
            return true;

        return false;

    }

    public void addAllEvents(Event e) {
        // nodeLE Cnode= new nodeLE(e);
        nodeLE temp = new nodeLE(e);
        if (empty()) {
            current = head = temp;

        } else if (Before(head.getData(), e)) {
            temp.setNext(head);
            head = temp;

        } else {
            findFirst(); // current=head;

            while (current.getNext() != null) {
                if ((Before(current.getNext().getData(), e))) {
                    temp.setNext(current.getNext());
                    current.setNext(temp);
                    current = temp;

                }
                /*
                 * current.setNext(temp);
                 * current=temp;
                 */
                if (current.getNext() == null) {
                    current.setNext(temp);
                }

            }

        }

    }

    public boolean empty() {
        return head == null;
    }

    public void findFirst() {
        current = head;
    }

    public void findNext() {
        current = current.getNext();
    }

    

}