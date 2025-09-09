public class TreeSet {
    int counter = 0;
    Node root;
    public TreeSet() {
        Node root = null;
    }

    public boolean contains(int value) {
        Node iter = root;

        while (iter != null) {
            if(value == iter.data) {return true;}
            if (iter.data < value) {
                iter = iter.right;
            }
            else {
                iter = iter.left;
            }
        }
        return false; 
    }

    public void insert(int value) {
        Node iter = root;
        Node previous;
        Node ny = new Node(value);

        if (root == null) {
            root = ny; 
            counter++;       
        }
        while (true) {
            if(value == iter.data) {return;}
            previous = iter;
            if (iter.data < value) {
                iter = iter.right;
                if (iter == null) {
                    counter++;
                    previous.right = ny;
                    return;
                }
            }
            else {
                iter = iter.left;
                if (iter == null){
                    counter++;
                    previous.left = ny;
                    return;
                }
            }
        } 
    }

    public void remove(int value) {
        counter--;
    }

    public int size() {
        return counter;
    }

    public static void main(String[] args) {

    }
}
