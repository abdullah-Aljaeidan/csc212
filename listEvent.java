
public class listEvent {
    private nodeLE current;
    private nodeLE head;

    public listEvent() {
        current = head = null;
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

    public void addEvent(Event e) {
        nodeLE temp = new nodeLE(e);
        if (empty())
            current = head = temp;
        else {
            temp.setNext(current.getNext());
            current.setNext(temp);
            current = temp;
        }
    }

    public void findConflict(Event e) {
        findFirst();
        while (current != null) {
            boolean checkDate = current.getData().getDate().equalsIgnoreCase(e.getDate());
            boolean checkTime = current.getData().getTime().equalsIgnoreCase(e.getTime());
            if (checkDate && checkTime) {
                System.out.println("Event conflicts with another event!");
                return;
            }
            addEvent(e);
        }
    }

}