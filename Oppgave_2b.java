//@olavwa @isakfe oblig1 15.09.2025

import java.util.PriorityQueue;

class Oppgave_2b{
    public Oppgave_2b(){

    }

    public void skrivut(PriorityQueue<Integer> inputs) {
    if (inputs.isEmpty()) return; // stopp hvis listen er tom

    int midt = (inputs.size()) / 2;

    PriorityQueue<Integer> inputs1 = new PriorityQueue<>();

    for (int i = 0; i<midt; i++) {
        inputs1.offer(inputs.poll());
    }
    System.out.println(inputs.poll());  

    // rekursivt kall
    if (!inputs.isEmpty()) skrivut(inputs);
    if (!inputs1.isEmpty()) skrivut(inputs1);
    
    
    
}


    public static void main(String[] args){
        Oppgave_2b pikaboo = new Oppgave_2b();
        PriorityQueue<Integer> test = new PriorityQueue<>();
        for (int i = 0; i < 11; i++){
            test.offer(i);
        }
        pikaboo.skrivut(test);
    }

    
}
