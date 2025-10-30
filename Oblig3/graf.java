import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;


// ikke noe som fungerer mere tanker og litt oppsett (stackoverflow) noe som feiler og den looper tror jeg.
public class Graf {
    HashMap<Actor, Set<Edge>> graf;
    ArrayList<Actor> actor;
    Map<String, Movie> movie;


    private int teller = 0;

    public Graf( HashMap<Actor ,Set<Edge>> graf,ArrayList<Actor> actor, Map<String, Movie> movie){
        this.graf = graf;
        this.actor = actor;
        this.movie = movie;
    }

    public void komponenter() {
        HashSet<Actor> besøkt = new HashSet<>();
        HashMap<Integer, Integer> komponentStr = new HashMap<>();

        for (Actor a : actor) {
            if (!besøkt.contains(a)) {
                int størrelse = komp_iterativ(a, besøkt);
                komponentStr.put(størrelse, komponentStr.getOrDefault(størrelse, 0 ) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : komponentStr.entrySet()) {
            System.out.println("There are " + entry.getValue() + " components of size " + entry.getKey());
        }
    }

    private int komp_iterativ(Actor start, HashSet<Actor> besøkt) {
        Stack<Actor> stack = new Stack<>();
        stack.push(start);
        teller = 0;

        while (!stack.isEmpty()) {
            Actor nåværende = stack.pop();
            if (besøkt.contains(nåværende)) {
                continue;
            }
            besøkt.add(nåværende);
            teller++;

            Set<Edge> kanter = graf.get(nåværende);
            if (kanter != null) {
                for (Edge k : kanter) {
                    Actor nabo = k.getToActor();
                    if (!besøkt.contains(nabo)) {
                        stack.push(nabo);
                    }
                }
            }
        }
        return teller;
    }

    /* 
    public void komponenter(){
        HashSet<Actor> tidligere = new HashSet<>();
        HashMap<Integer, Integer> utskrift = new HashMap<>();

        for (Actor a: actor){
            if (!tidligere.contains(a)){
                komp_rek(a, tidligere); 
                utskrift.put(teller, utskrift.getOrDefault(teller, 0) + 1);
                teller = 0;
            }
        } 

        for (Map.Entry<Integer, Integer> entry : utskrift.entrySet()) {
            System.out.println("There are " + entry.getValue() + " components of size " + entry.getKey());
        }
        System.out.println("Kom meg hit!");
    }*/

    /* 
    private void komp_rek(Actor a, HashSet<Actor> tidligere){
        // rekursive delen tar imot hvor den var og kansje hashset kansje 
        tidligere.add(a);
        teller++;
        Set<Edge> edges = graf.get(a);
        if (edges != null){
            for (Edge e: edges){
                Actor nabo = e.getToActor();
                if(!tidligere.contains(nabo)){
                    komp_rek(nabo, tidligere);    
                }
            }
        }
    }*/

}
