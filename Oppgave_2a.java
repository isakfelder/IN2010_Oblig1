import java.util.ArrayList;
import java.util.Arrays;

class Oppgave_2a{
    public Oppgave_2a(){

    }

    public void skrivut(ArrayList<Integer> inputs) {
    if (inputs.isEmpty()) return; // stopp hvis listen er tom

    int midt = (inputs.size()) / 2;
    int midtVerdi = inputs.remove(midt); // fjern og hent midterste verdi
    System.out.println(midtVerdi);       // print midtverdien

    ArrayList<Integer> inputs1 = new ArrayList<>();
    int halv = inputs.size() / 2;

    for (int i = 0; i < halv; i++) {
        inputs1.add(inputs.remove(0));
    }

    // rekursivt kall
    if (!inputs1.isEmpty()) skrivut(inputs1);
    if (!inputs.isEmpty()) skrivut(inputs);
}


    public static void main(String[] args){
        Oppgave_2a pikaboo = new Oppgave_2a();
        ArrayList<Integer> test = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16,17,18,19,20));
        pikaboo.skrivut(test);
    }

    
}