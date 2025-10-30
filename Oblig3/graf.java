import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


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

    }

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
    }

}
