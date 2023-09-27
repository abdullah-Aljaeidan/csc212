
public class nodeLE {
    private Event data;
    private nodeLE next;

    public nodeLE(Event data) {
        this.data = data;
        this.next = null;
    }

    public Event getData() {
        return data;
    }

    public void setNext(nodeLE next) {
        this.next = next;
    }

    public nodeLE getNext() {
        return next;
    }

    public void setData(Event data) {
        this.data = data;
    }
}