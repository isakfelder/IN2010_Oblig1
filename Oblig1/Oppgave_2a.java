package Oblig1;
//@olavwa @isakfe oblig1 15.09.2025

import java.util.ArrayList;

class testing{
    public testing(){

    }

    public void skrivut(ArrayList<Integer> inputs) {
    if (inputs.isEmpty()) return; // stopp hvis listen er tom

    ArrayList<Integer> inputs1 = new ArrayList<>();

    int midt = (inputs.size()) / 2;
    for (int i = 0; i<midt; i++) {
        inputs1.add(inputs.remove(0));
    }
    System.out.println(inputs.remove(0)); 

    // rekursivt kall
    if (!inputs.isEmpty()) skrivut(inputs);
    if (!inputs1.isEmpty()) skrivut(inputs1);
    
}


    public static void main(String[] args){
        testing pikaboo = new testing();
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 11; i++){
            test.add(i);
        }
        pikaboo.skrivut(test);
    }

    
}