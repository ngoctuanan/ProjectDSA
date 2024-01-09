package GameEngine;

public class Node {
    private long score,time;
    private Node nextNode; 
    public Node(long data, long time ){
        this.score = data;
        this.time = time;
    }

    public int compareTo(Node another){
        if (this.score > another.score) return 1;
        else
            if(this.score < another.score) return -1;
            else
                if(this.time < another.time) return 1;
                else
                    if(this.time > another.time) return -1;
                    else
                        return 0;
    }

    public int compareTo(long score, long time){
        return this.compareTo(new Node(score, time));
    }
    public long getScore() {
        return score;
    }
    public void setData(int data) {
        this.score = data;
    }
    public Node getNextNode() {
        return nextNode;
    }
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return String.valueOf(score) + "          " + String.valueOf(time);
    }

}
