public class Node<T> {
    private Node<T> next;
    private T data;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setData(T data) {
        this.data = data;
    }

}
