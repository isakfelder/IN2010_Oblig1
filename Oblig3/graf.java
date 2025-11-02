import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

// ikke noe som fungerer mere tanker og litt oppsett (stackoverflow) noe som feiler og den looper tror jeg.
public class Graf{
    HashMap<Actor, Set<Edge>> graf;
    ArrayList<Actor> actor;
    Map<String, Movie> movie;


    public Graf( HashMap<Actor ,Set<Edge>> graf,ArrayList<Actor> actor, Map<String, Movie> movie){
        this.graf = graf;
        this.actor = actor;
        this.movie = movie;
    }

    public void komponenter() {
        HashSet<Actor> besøkt = new HashSet<>();
        TreeMap<Integer, Integer> komponentStr = new TreeMap<>(Collections.reverseOrder());

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
        int størrelse = 0;

        while (!stack.isEmpty()) {
            Actor nåværende = stack.pop();
            if (besøkt.contains(nåværende)) {
                continue;
            }
            besøkt.add(nåværende);
            størrelse++;

            Set<Edge> kanter = graf.get(nåværende);
            if (kanter != null) {
                for (Edge k : kanter) {
                    Actor nabo = k.getToActor(nåværende);
                    if (!besøkt.contains(nabo)) {
                        stack.push(nabo);
                    }
                }
            }
        }
        return størrelse;
    } 



    public void BFS(Actor start, Actor goal){
        if (start == goal) {
            System.out.println(start.getName());
            return;
        }

        HashMap<Actor, Edge> vei = new HashMap<>();
        Boolean finnes = iter_BFS(start,goal, vei);

        if (finnes == false) {
            System.out.println("Ingen sti funnet fra " + start.getName() + " til " + goal.getName());
            return;
        }
    

        Stack<String> utskrift = new Stack<>();
        Actor current = goal;
        while(current != start){
            
            String reversed = (
                vei.get(current).getToActor(current).getName() + 
                " =====> [ " + vei.get(current).getFilm_Navn() + " ] =====> " + 
                current.getName());
                
                utskrift.add(reversed);
                
            current = vei.get(current).getToActor(current);
        }
        while(!utskrift.isEmpty()){System.out.println(utskrift.pop());}

    }
    
    public Boolean iter_BFS(Actor start, Actor goal,HashMap<Actor, Edge> vei){
        Queue<Actor> kø = new LinkedList<>();
        HashSet<Actor> besøkt = new HashSet<>();

        kø.add(start);
        besøkt.add(start);
        vei.put(start, null);

        while(!kø.isEmpty()) {
            Actor nåværende = kø.poll(); 
            Set<Edge> kanter = graf.get(nåværende);

            if (kanter != null) {

                for (Edge k : kanter) {
                    Actor nabo = k.getToActor(nåværende);

                    if (!besøkt.contains(nabo)) {
                        kø.add(nabo);
                        besøkt.add(nabo);
                        vei.put(nabo,k);

                        if (nabo == goal){ return true; }
                    }
                    
                }
            }
        } 
        return false;
    } 
    
}
