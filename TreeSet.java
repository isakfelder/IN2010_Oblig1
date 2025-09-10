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
                    ny.parent = previous;
                    return;
                }
            }
            else {
                iter = iter.left;
                if (iter == null){
                    counter++;
                    previous.left = ny;
                    ny.parent = previous;
                    return;
                }
            }
        } 
    }

    public void remove(int value) {
        Node iter = root;
        Node previous;

        while (iter != null) {
            previous = iter;

            /*
            if (iter.data == value) {
                if (iter.left == null || iter.right == null) {
                    previous.right = null;
                    iter.parent = null;
                    counter--;
                    return;
                }
            }  
            må fikses på men forhåpentligvis ikke trengt
            må bare sette referanser riktig i de to under    
            */

            if (iter.data < value) {
                iter = iter.right;
                if(value == iter.data) {
                    if (iter.left == null || iter.right == null) {
                        previous.right = null;
                        iter.parent = null;
                        counter--;
                        return;
                    }
                    else {
                        previous.right = iter.right;
                        iter.right.parent = iter.parent;
                        counter--;
                        return;
                    }
                }
            }
            else {
                iter = iter.left;
                //hvis iter er root
                if (iter.parent == null) {
                    
                }

                //hvis iter er i midten
                
                
                //hvis iter er i bunn
                }
            }
        }
    }

    public int size() {
        return counter;
    }

    public static void main(String[] args) {

    }
}
