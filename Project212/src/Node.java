public class Node{
    private Contact data;
    private Node next;
    private Node previous;

    public Node(Contact data) {
        this.data = data;
        this.next = null; 
    }
    public Contact getData() {
        return data;
    }

    
    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }
    public void setData(Contact data) {
        this.data = data;
    }
    public Node getPrevious() {
        return previous;
    }
    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    
}
