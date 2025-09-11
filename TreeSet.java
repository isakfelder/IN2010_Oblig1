import java.util.Scanner;

public class TreeSet {
    int counter = 0;
    Node root;
    public TreeSet() {
        this.root = null;
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
            return;      
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
        if (root.data == value) {
            if (root.left == null && root.right == null) {
                root = null;
                counter--;
                return;
            }
            if (root.right == null && root.left != null) {
                root = root.left;
                root.parent = null;
                counter--;
                return;
            }
            if (root.left == null && root.right != null) {
                root = root.right;
                root.parent = null;
                counter--;
                return;
            }
            else {
                iter = root.right;
                while (iter.left != null) {
                    iter = iter.left;
                }
                root.data = iter.data;
                iter.parent.left = null;
                iter.parent = null;
                counter--;
                return;
            }
        }
        while (iter != null) {
            //sjekk om node er value
            if (iter.data == value) {
                //hvis noden har 0 barn
                if (iter.left == null && iter.right == null) {
                    //sjekk om den er på høyre eller venstre side
                    if (iter == iter.parent.right) {
                        iter.parent.right = null;
                        counter--;
                        return;
                    }
                    if (iter == iter.parent.left) {
                        iter.parent.left = null;
                        counter--;
                        return;
                    }
                }

                //hvis noden har 1 barn
                if (iter.left == null && iter.right != null || iter.right == null && iter.left != null) {
                    //sjekk hvilken side barnet er på
                    //node.previous peker på node.barn
                    if (iter == iter.parent.right) {
                        if (iter.left == null) {
                            iter.parent.right = iter.right;
                            iter.right.parent = iter.parent;
                            iter.parent = null;
                            iter.right = null;
                            counter--;
                            return;
                        }
                        if (iter.right == null) {
                            iter.parent.right = iter.left;
                            iter.left.parent = iter.parent;
                            iter.parent = null;
                            iter.left = null;
                            counter--;
                            return;
                        }
                    }
                    if (iter == iter.parent.left) {
                        if (iter.left == null) {
                            iter.parent.left = iter.right;
                            iter.right.parent = iter.parent;
                            iter.parent = null;
                            iter.right = null;
                            counter--;
                            return;
                        }
                        if (iter.right == null) {
                            iter.parent.left = iter.left;
                            iter.left.parent = iter.parent;
                            iter.parent = null;
                            iter.left = null;
                            counter--;
                            return;
                        }
                    }
                }

                //hvis noden har 2 barn
                    if (iter.right != null && iter.left != null) {
                        //gå til den mest venstre verdien på høyre subtre, sett denne noden til iter.value, iter.previous.right/left 
                        //(må sjekke hvilken side den er på), må oppdate referanser til den som blir flyttet også
                        Node iter2 = iter.right;
                        while (iter2.left != null) {
                            iter2 = iter2.left;
                        }

                        if (iter2.right != null) {
                            iter.data = iter2.data;
                            iter2.parent.left = iter2.left;
                            iter2.right.parent = iter2.parent;
                            iter2.parent.left = null;
                            iter2.parent = null;
                            counter--;
                            return;
                        }

                        iter.data = iter2.data;
                        iter2.parent.left = null;
                        iter2.parent = null;
                        counter--;
                        return;
                    }
            }

            //er node større eller mindre enn iter
            //hvis større, gå høyre
            if (iter.data > value) {
                iter = iter.right;
            }
            //hvis mindre (ellers), gå venstre
            else {
                iter = iter.left;
            }
        }
        return;
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
            //String instruksjon = delt[0];
            //int verdi = Integer.parseInt(delt[1]);


            //String[] delt = input.split(" ");

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
            }
        }
    }
}
