package GameEngine;

public class SortedLinkedList {
    private Node head;
    private Node last;

    public SortedLinkedList(){
        
    }
    public void add (int data ){
        if(head == null){
            this.head = new Node(data);
            this.last = head;
            return;
        }

        Node current = head;
        
        while (current.getNextNode()!= null && current.getData() > data) {
            current = current.getNextNode();
        }

        if(current == head ) {
            head = new Node(data);
            head.setNextNode(current);
        }
        else{
            Node newNode = new Node(data);

            newNode.setNextNode(current.getNextNode());
            current.setNextNode(newNode);

            if(newNode.getNextNode() == null) last = newNode;
        }
    }
    
//--------------------------------------
    public Node getHead() {
        return head;
    }
    public void setHead(Node head) {
        this.head = head;
    }
    public Node getLast() {
        return last;
    }
    public void setLast(Node last) {
        this.last = last;
    }
}

