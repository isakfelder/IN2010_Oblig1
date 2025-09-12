import java.util.Scanner;

public class AVL_tree {
    int counter = 0;
    AVLnode root = null;

    public AVL_tree(){

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

    

    public int size() {
        return counter;
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

    //itterativ
    public void insert1(int value){
        AVLnode iter = root;
        AVLnode previous;
        AVLnode ny = new AVLnode(value);

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
                    previous.right = ny;
                    ny.parent = previous;
                    counter++;
                    AVLnode current = ny;

                    while (current.parent != null){
                        current = current.parent;

                        int leftH;
                        if (current.left == null) {
                            leftH = -1;
                        } else {
                            leftH= current.left.dybde;
                        }  
                        int rightH;
                        if (current.right == null) {
                            rightH = -1;
                        } else {
                            rightH = current.right.dybde;
                        }
                        current.dybde = 1 + Math.max(rightH, leftH);
                        
                    }

                    return;
                }
            }
            else {
                iter = iter.left;

                if (iter == null){
                    previous.left = ny;
                    ny.parent = previous;
                    counter++;

                    AVLnode current = ny;

                    while (current.parent != null){
                        current = current.parent;

                        int leftH;
                        if (current.left == null) {
                            leftH = -1;
                        } else {
                            leftH= current.left.dybde;
                        }  
                        int rightH;
                        if (current.right == null) {
                            rightH = -1;
                        } else {
                            rightH = current.right.dybde;
                        }
                        current.dybde = 1 + Math.max(rightH, leftH);
                        
                    }

                    return;
                }
            }
        } 
    }

    
    // insert kallet som kan kalle balanser etter insertRec
    public void insert(int value) {
        root = insertRec(root, value);
        counter++;
    }

    // Rekursiv insert fra roten så vi slipper parent i rotate, mere pekere og flytte mere kode da.
    private AVLnode insertRec(AVLnode node, int value) {
        if (node == null) {
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

         // skjekker at de ikke er 0 i høyden
        int leftH;
        if (node.left == null) {
            leftH = -1;
        } else {
            leftH= node.left.dybde;
        }  
        int rightH;
        if (node.right == null) {
            rightH = -1;
        } else {
            rightH = node.right.dybde;
        }


        node.dybde = 1 + Math.max(leftH, rightH);

        return node;
    }

    public void remove(int value) {
        AVLnode iter = root;
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
                        AVLnode iter2 = iter.right;
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
            if (iter.data < value) {
                iter = iter.right;
                System.out.println("går høyre");
            }
            //hvis mindre (ellers), gå venstre
            else {
                iter = iter.left;
                System.out.println("går venstre");
            }
        }        
    }

    public void remove2(int value){
        root = removeRec(root, value);
        counter--;

    }
    
    
    private AVLnode removeRec(AVLnode node, int value){

        if (node == null){

        }
    
            // rekursiv obdatering av node dybde
    
        if (value < node.data) {
    
            node.left = insertRec(node.left, value);
    
        } else if (value > node.data) {
    
            node.right = insertRec(node.right, value);
    
        } else {
    
            return node; // duplikat
    
        }


    
        // skjekker at de ikke er 0 i høyden
    
        int leftH;
    
        if (node.left == null) {
    
            leftH = -1;
    
        } else {
    
            leftH= node.left.dybde;
    
        }  
    
        int rightH;
    
        if (node.right == null) {
    
            rightH = -1;
    
        } else {
    
            rightH = node.right.dybde;
    
        }


        node.dybde = 1 + Math.max(leftH, rightH);


    
        return node;


    
    }

    // har ikke fikset dybde vet begynner tro rekusjon er bedre.
    public void remove1(int value) {
        AVLnode iter = root;
        if (root.data == value) {
            if (root.left == null && root.right == null) {
                root = null;
                counter--;
                return;
            }
            if (root.right == null && root.left != null) {
                root = root.left;
                counter--;
                return;
            }
            if (root.left == null && root.right != null) {
                root = root.right;
                counter--;
                return;
            }
            else {
                iter = root.right;
                while (iter.left != null) {
                    iter = iter.left;
                }
                root.data = iter.data;
                counter--;
                return;
            }
        }
        AVLnode forrige = root;
        while (iter != null) {

            //sjekk om node er value
            if (iter.data == value) {
                //hvis noden har 0 barn
                if (iter.left == null && iter.right == null) {
                    //sjekk om den er på høyre eller venstre side
                    if (iter == forrige.right) {
                        forrige.right = null;
                        counter--;
                        return;
                    }
                    if (iter == forrige.left) {
                        forrige.left = null;
                        counter--;
                        return;
                    }
                }

                //hvis noden har 1 barn
                if (iter.left == null && iter.right != null || iter.right == null && iter.left != null) {
                    //sjekk hvilken side barnet er på
                    //node.previous peker på node.barn
                    if (iter == forrige.right) {
                        if (iter.left == null) {
                            forrige.right = iter.right;
                            counter--;
                            return;
                        }
                        if (iter.right == null) {
                            forrige.right = iter.left;
                           
                            counter--;
                            return;
                        }
                    }
                    if (iter == forrige.left) {
                        if (iter.left == null) {
                            forrige.left = iter.right;
                            counter--;
                            return;
                        }
                        if (iter.right == null) {
                            forrige.left = iter.left;
                            counter--;
                            return;
                        }
                
                    }

                    

                }

                //hvis noden har 2 barn
                    if (iter.right != null && iter.left != null) {
                        //gå til den mest venstre verdien på høyre subtre, sett denne noden til iter.value, iter.previous.right/left 
                        //(må sjekke hvilken side den er på), må oppdate referanser til den som blir flyttet også
                        AVLnode forrige2 = iter;
                        AVLnode iter2 = iter.right;
                        while (iter2.left != null) {
                            forrige2 = iter2;
                            iter2 = iter2.left;
                        }


                        if (iter2.right != null) {
                            iter.data = iter2.data;
                            forrige2.left = null;
                            forrige2.left = iter2.right;
                            counter--;
                            return;
                        }

                        iter.data = iter2.data;
                        forrige2.left = null;
                        
                        counter--;
                        return;
                    }        
            }

            //er node større eller mindre enn iter
            //hvis større, gå høyre
            if (iter.data < value) {
                forrige = iter;
                iter = iter.right;
                System.out.println("går høyre");
            }
            //hvis mindre (ellers), gå venstre
            else {
                forrige = iter;
                iter = iter.left;
                System.out.println("går venstre");
            }
        }        
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
                    tree.insert1(verdi);
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
            }
        }
    }


}


