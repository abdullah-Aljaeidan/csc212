public class LinkedListADT {

	private Node head;
	private Node current;

	public LinkedListADT() {
		head = current = null;
	}

	public boolean empty() {
		return head == null;
	}

	public boolean SearchTool(Contact c){
    if(empty())
    	return false;
    
    else if(head!=null) {
        
        while(current!=null) {
            if(current.getData().getName().equals(c.getName()) || current.getData().getPhoneNumber()==(c.getPhoneNumber()))
             return true;
        }
             current=current.getNext();
        
    }
    return false;
    
	}
	public void addContact(Contact c ){
	    Node tmp= new Node(c);

	    if(empty())
	    	head= tmp;

	    else{

	    if(SearchTool(c))
	        System.out.println("repeated");
	    
	    }
	    if(c.compareTo(head.getData()) < 0) {
            tmp.setNext(head);
            head = tmp;
            return;
        }
	    Node placeNode=head;
        while(placeNode.getNext()!=null){

           if (c.compareTo(placeNode.getNext().getData()) < 0) {
          tmp.setNext(placeNode);
          placeNode.setNext(tmp);
          return;
           }
       }
        placeNode=placeNode.getNext();
	}

}
