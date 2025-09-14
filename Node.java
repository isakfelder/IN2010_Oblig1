public class Node {
    int data;
    Node parent;
    Node right;
    Node left;

    public Node(int data) {
        this.data = data;
        this.parent = null;
        this.right = null;
        this.left = null;
    }

    public int getData() {
        return data;  
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
    
    public void setParent(Node parent) {
        this.parent = parent;
    }
}
