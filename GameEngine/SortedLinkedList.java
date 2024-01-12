package GameEngine;

public class SortedLinkedList {
    private Node head;
    private Node last;

    public SortedLinkedList(){
        
    }
    public void add (long score,long time ){

        Node newNode = new Node(score,time);

        if(head == null){
            this.head = newNode;
            this.last = head;
            return;
        }

        Node current = head;
        
        while (current.getNextNode()!= null && current.getNextNode().compareTo(newNode) == 1) {
            current = current.getNextNode();
        }

        if(current == head ) {
            if(head.compareTo(newNode) == 1) { 
                head.setNextNode(newNode);
                return;
            }
            head = newNode;
            head.setNextNode(current);
        }
        else{
            newNode.setNextNode(current.getNextNode());
            current.setNextNode(newNode);

            if(newNode.getNextNode() == null) last = newNode;
        }
    }
    
    @Override
    public String toString() {
        Node current = head;
        String result = "<html><pre>SCORE\tTIME";
        int i = 0;
        while (current != null && i < 10) {
            result = result +"\n"+ current.toString();
            current = current.getNextNode();
            i++;    
        }
        result = result + "</pre></html";
        return result;
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
    public void display (){
        Node  current = head; 

        while ( current != null) {
            System.out.println(current);
            current = current.getNextNode();
        }
    }
}

