import java.util.Scanner;

public class AVL_tree {
    int counter = 0;
    AVLnode root = null;

    public AVL_tree(){
    }

    private class AVLnode {
        int data;
        int dybde;
        AVLnode right = null;
        AVLnode left = null;

        public AVLnode(int data) {
            this.data = data;
            dybde = 0;
        }
    }

    // insert kallet som kan kalle balanser etter insertRec
    public void insert(int value) {
        root = insertRec(root, value);
    }

    // Rekursiv insert fra roten så vi slipper parent i rotate, mere pekere og flytte mere kode da.
    private AVLnode insertRec(AVLnode node, int value) {
        if (node == null) {
            counter++;
            return new AVLnode(value); // ny node som blad 
        }

        // rekursiv obdatering av node dybde
        if (value < node.data) {
            node.left = insertRec(node.left, value);
        } else if (value > node.data) {
            node.right = insertRec(node.right, value);
        } else {
            return node; // duplikat
        }

        update_dybde(node);
        //balanser(node); //balanse ved rekursiv veldig lett
        return node;
    }

    public void remove(int value){
        root = removeRec(root, value);
    }

    public AVLnode removeRec(AVLnode node, int value) {
        if (node == null) return null;

        if (node.data > value) {
            node.left = removeRec(node.left, value );
        }
        else if (node.data < value) {
            node.right = removeRec(node.right, value);

        } else {
            if (node.right == null) {
                counter--;
                return node.left;
            }

            else if(node.left == null) {
                counter--;
                return node.right;

            } else {
                AVLnode midlertidig = finnlav(node.right);
                node.data = midlertidig.data;
                node.right = removeRec(node.right, midlertidig.data); 
            }
        }

        update_dybde(node);
        //return balanser(node); //balanse ved rekursiv veldig lett for ikke parent pekere
        return node;// trengs ikke mener jeg
    }

    //finner den som skal ta over for i remove så man slipper re skrive kode
    public AVLnode finnlav(AVLnode node){

        AVLnode min_H = node;
        while(min_H.left != null){
            min_H = min_H.left;
        }
        return min_H;
    }

    // setter dybden til noeden hvis de under har riktig verdi 
    public void update_dybde(AVLnode node){
        if (node == null) return;
        node.dybde = 1 + Math.max(dybde_beskyttet(node.left), dybde_beskyttet(node.right));
    }

    //dybde skjekk ikke null verdi
    public int dybde_beskyttet(AVLnode node){
        if (node == null) {
            return -1;
        } 
        return node.dybde;
    }

    public boolean contains(int value) {
        AVLnode iter = root;

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

    public int size() {
        return counter;
    }

    public int getDybde(int value){
        AVLnode iter = root;

        while (iter != null) {
            if(value == iter.data) {
                return iter.dybde;
            }

            if (iter.data < value) {
                iter = iter.right;
            }
            else {
                iter = iter.left;
            }
        }
        System.out.println("Finnes ikke, prøv igjen!");
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVL_tree tree = new AVL_tree();

        while (true) {
            String input = scanner.nextLine();

            String[] delt = input.split(" ");

            String instruksjon = delt[0];
            int verdi = 0;
            if (delt.length > 1) {
                verdi = Integer.parseInt(delt[1]);
            }

            switch (instruksjon) {
                case "insert":
                    tree.insert(verdi);
                    break;
                case "contains":
                    System.out.println(tree.contains(verdi));
                    break;
                case "dybde":
                    System.out.println(tree.getDybde(verdi));
                    break;
                case "size":
                    System.out.println(tree.size());
                    break;
                case "remove":
                    tree.remove(verdi);
                    System.out.println(verdi);
                    break;
                case "stop":
                    scanner.close();
                    System.exit(1);
            }
        }
    }
}




