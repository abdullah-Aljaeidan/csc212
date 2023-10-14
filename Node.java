public class Node<T> {
    private Node<T> next;
    private T data;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    //Returns the data of the node
    public T getData() {
        return data;
    }

    //Sets the next node
    public void setNext(Node<T> next) {
        this.next = next;
    }

    //Returns the next node
    public Node<T> getNext() {
        return next;
    }

    //Sets the data of the node
    public void setData(T data) {
        this.data = data;
    }

}
