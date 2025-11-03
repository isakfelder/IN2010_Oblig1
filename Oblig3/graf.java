// @isakfe / olavwa oblig3 in2010 h25

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

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
                " =====> [ " + vei.get(current).getFilm_Navn() + " (" + vei.get(current).getRating()  + ")  ] =====> " + 
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



    class node_distanse implements Comparable<node_distanse>{
        float rating;
        Actor actor;
        
        public node_distanse(Actor actor, float rating){
            this.actor = actor;
            this.rating = rating;
        }

        @Override
        public int compareTo(node_distanse node) {
            if(rating > node.rating){return 1;}
            else if(rating < node.rating){return -1;}
            else{return 0;}
        }
    }

    public void chill(Actor start, Actor goal){
        HashMap<Actor, Float> dist = new HashMap<>();
        HashMap<Actor, Edge> vei = new HashMap<>();
        Boolean finnes = iter_chill(start, goal, vei, dist);

        if (start == goal) {
            System.out.println(start.getName());
            return;
        }

        if (finnes == false) {
            System.out.println("Ingen sti funnet fra " + start.getName() + " til " + goal.getName());
            return;
        }
    

        Stack<String> utskrift = new Stack<>();
        Actor current = goal;
        while(current != start){
            
            String reversed = (
                vei.get(current).getToActor(current).getName() + 
                " =====> [ " + vei.get(current).getFilm_Navn() + " (" + vei.get(current).getRating()  + ")  ] =====> " + 
                current.getName());
                
                utskrift.add(reversed);
                
            current = vei.get(current).getToActor(current);
        }
        while(!utskrift.isEmpty()){System.out.println(utskrift.pop());}
        String weigth = String.format(Locale.US,"%.1f", dist.get(goal));
        System.out.println("Total weigth: " + weigth);

    }

        
    

    public Boolean iter_chill(Actor start, Actor goal,HashMap<Actor, Edge> vei, HashMap<Actor, Float> dist){
        PriorityQueue<node_distanse> kø = new PriorityQueue<>();
        float tall = Float.MAX_VALUE; //chilleste vei en så lenge
        boolean retur = false;

        node_distanse current = new node_distanse( start , 0f);
        kø.offer(current);  
        dist.put(start , 0f);

        while(!kø.isEmpty()) {
            node_distanse nåværende = kø.poll(); 
            Set<Edge> kanter = graf.get(nåværende.actor);

            if (nåværende.rating > tall) continue;

            if (kanter != null) {

                for (Edge k : kanter) {
                    Actor nabo = k.getToActor(nåværende.actor);
                    node_distanse nabo1 = new node_distanse(nabo,nåværende.rating + 10-k.getRating() );

                        
                        if (nabo1.rating < dist.getOrDefault(nabo, Float.MAX_VALUE)) {
                            dist.put(nabo, nabo1.rating);
                            vei.put(nabo, k);
                            kø.offer(nabo1);  // ← LEGG TIL UANSETT (men bare hvis bedre)

                            if (nabo == goal){ 
                                tall = nabo1.rating;
                                retur = true;
                            }
                        } 
                }
            }
        } 
        return retur;
    } 
    
}
