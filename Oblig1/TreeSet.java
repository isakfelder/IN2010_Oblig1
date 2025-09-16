package Oblig1;
//@olavwa @isakfe oblig1 15.09.2025

import java.util.Scanner;

public class TreeSet {
    int counter = 0;
    Node root;
    public TreeSet() {
        this.root = null;
    }

    private class Node {
        int data;
        Node right = null;
        Node left = null;

        public Node(int data) {
            this.data = data;
        }
   
    }

      // insert kallet som kan kalle balanser etter insertRec
    public void insert(int value) {
        root = insertRec(root, value);
        
    }

    // Rekursiv insert fra roten så vi slipper parent i rotate, mere pekere og flytte mere kode da.
    private Node insertRec(Node node, int value) {
        if (node == null) {
            counter++;
            return new Node(value); // ny node som blad 
        }

        // rekursiv obdatering av node dybde
        if (value < node.data) {
            node.left = insertRec(node.left, value);
        } else if (value > node.data) {
            node.right = insertRec(node.right, value);
        } else {
            return node; // duplikat
        }


        return node; //balanse ved rekursiv veldig lett
    }
    

    public void remove(int value){
        root = removeRec(root, value);
        
    }

    public Node removeRec(Node node, int value){

        if (node == null) return null;

        if (node.data > value){
            node.left = removeRec(node.left, value );
        }
        else if (node.data < value){
            node.right = removeRec(node.right, value);

        }else{

            if (node.right == null){
                counter--;
                return node.left;
            }

            else if(node.left == null){
                counter--;
                return node.right;

            }else{
                Node midlertidig = finnlav(node.right);
                node.data = midlertidig.data;
                node.right = removeRec(node.right, midlertidig.data); 
            }
        
        }

        return node; //balanse ved rekursiv veldig lett for ikke parent pekere
        
    }

    public Node finnlav(Node node){

        Node min_H = node;
        while(min_H.left != null){
            min_H = min_H.left;
        }
        return min_H;

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

    public int size() {
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeSet tree = new TreeSet();

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
                case "remove":
                    tree.remove(verdi);
                    break;
                case "size":
                    System.out.println(tree.size());
                    break;
                case "stop":
                    scanner.close();
                    System.exit(1);
            }
        }
    }
}
