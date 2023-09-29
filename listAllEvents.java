
public class listAllEvents {

    nodeLE head;
    nodeLE current;
    nodeLE Cnode;

    listAllEvents() {
        head = current = null;
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

    public boolean findConflict(String date, String time) {
        findFirst();
        while (current != null) {
            boolean checkDate = current.getData().getDate().equalsIgnoreCase(date);
            boolean checkTime = current.getData().getTime().equalsIgnoreCase(time);
            if (checkDate && checkTime) {

                return false;
            }

        }
        // addEvent(e);
        return true;

    }

    public void printAllContactEvent(String name) {
        if (empty()) {
            System.out.println("No events scheduled!");
            return;
        }
        findFirst();
        int countPrints = 0;
        while (current != null) {
            if (current.getData().getEventParticipant().getName().equalsIgnoreCase(name)) {
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
        findFirst();
        while (current != null) {
            if (current.getData().getTitle().equalsIgnoreCase(eventTitle)) {
                System.out.println(current.getData().toString());
                return;
            }
        }
        System.out.println("No event found!");

    }
}