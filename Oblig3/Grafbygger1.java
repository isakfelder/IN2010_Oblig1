import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Grafbygger1 {
    private Map<Actor, Set<Edge>> adjGraf;
  
    public static void main(String[] args) {
        //System.out.println("Current working directory: " + System.getProperty("user.dir"));
        //lager den tomme i main så den har tilgang til den siden den er static
        HashMap<Actor, Set<Edge>> adjGraf = new HashMap<>();

        //av en eller annen merkelig grunn så er ikke working directoriet mitt i oblig 3, så da fungerer ikke filene her
        
        //String ActorsPath = "marvel_actors.tsv";
        String ActorsPath = "actors.tsv";
        //String ActorsPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig3\\marvel_actors.tsv";
        File ActorsFil = new File(ActorsPath);

        //String MoviesPath = "marvel_movies.tsv";
        String MoviesPath = "movies.tsv";
        //String MoviesPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig3\\marvel_movies.tsv";
        File MoviesFil = new File(MoviesPath);

        Grafbygger1 g = new Grafbygger1(adjGraf);


        HashMap<String, Actor> hash_actors = new HashMap<>();
        ArrayList<Actor> actors = les_og_bygg_Actors(ActorsFil, hash_actors );
        Map<String, Movie> movieMap = les_og_bygg_Movies(MoviesFil);

        g.byggGraf(actors, movieMap);

        //System.out.println(g.adjGraf);

        //System.out.println(g.getActorCount());
        //System.out.println(g.getEdgeCount());

        Graf tester = new Graf(adjGraf, actors, movieMap);
        //tester.komponenter();
        System.out.println("starter på " + hash_actors.get("nm0637259").getName()); // Tuva Novotny
        System.out.println("slutter på " + hash_actors.get("nm0931324").getName()); // Michael K. Williams
        tester.BFS(hash_actors.get("nm0637259"),hash_actors.get("nm0931324" ));
        /*Tuva Novotny
        ===[ Dear Alice (5.5) ] ===> Danny Glover
        ===[ LUV (5.9) ] ===> Michael K. Williams 
        */

        System.out.println("starter på " + hash_actors.get("nm0424060").getName()); // Scarlett Johansson
        System.out.println("slutter på " + hash_actors.get("nm8076281").getName()); // Emma Mackey
        tester.chill(hash_actors.get("nm0424060"),hash_actors.get("nm8076281" ));
        /*Scarlett Johansson
        ===[ Avengers: Infinity War (8.4) ] ===> Ariana Greenblatt
        ===[ Barbie (7.1) ] ===> Emma Mackey
        Total weight: 4.5
        */
        
    }

    public Grafbygger1(Map<Actor, Set<Edge>> adjGraf) {
        this.adjGraf = adjGraf;
    }

    public void byggGraf(ArrayList<Actor> actors, Map<String, Movie> movieMap) {
        Map<String, Actor> actorById = new HashMap<>();
        
        //legg til skuespillere i adjgraf
        for (Actor a : actors) {
            adjGraf.put(a, new HashSet<>()); //dette er raskerere enn å kalle på addActor
            actorById.put(a.getId(), a);
        }

        //legg til skuespillere på hver film [filmid, skuespiller]
        Map<String, List<Actor>> skuespillereIFilm = new HashMap<>();
        for (Actor actor : actors) {
            for (String filmId : actor.getMovies()) {
                skuespillereIFilm.computeIfAbsent(filmId, k -> new ArrayList<>()).add(actor);
            }
        }
        //lag kanter
        for (Map.Entry<String, List<Actor>> entry : skuespillereIFilm.entrySet()) {
            String filmId = entry.getKey();
            List<Actor> skuespillere = entry.getValue();
            Movie film = movieMap.get(filmId);
            if (film == null) {
                // System.out.println("Fant ikke film med id: '" + filmId + "'");
                continue; // hopper over denne filmen
            }
            float rating = film.getRating();
            String film_name = film.getName();
            
            for (int i = 0; i < skuespillere.size(); i++) {
                for (int j = i + 1; j < skuespillere.size(); j++) {

                    Actor a1 = skuespillere.get(i);
                    Actor a2 = skuespillere.get(j);

                    adjGraf.putIfAbsent(a1, new HashSet<Edge>());
                    adjGraf.putIfAbsent(a2, new HashSet<Edge>());

                    Edge edge = new Edge(a1, a2, filmId, rating, film_name);
                    adjGraf.get(a1).add(edge);
                    adjGraf.get(a2).add(edge);
                }
            }
        }
        //return adjGraf;
    }

    public static ArrayList<Actor> les_og_bygg_Actors(File fil, HashMap<String, Actor> hash_actors) {
        ArrayList<Actor> actors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fil))) {
            String linje;

            while ((linje = br.readLine()) != null) {
                String[] deler = linje.split("\t");
                Actor ny = new Actor(deler[0], deler[1]);

                for (int i = 2; i < deler.length; i++) { //skal kjøre for hvor mange tt-ider hver skuespiller har
                    ny.addMovie(deler[i]);
                }
                actors.add(ny);
                hash_actors.put(deler[0], ny);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public static Map<String, Movie> les_og_bygg_Movies(File fil) {
        Map<String, Movie> movieMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fil))) {
            String linje;

            while ((linje = br.readLine()) != null) {
                String[] deler = linje.split("\t");
                Movie ny = new Movie(deler[0], deler[1], Float.parseFloat(deler[2]), Integer.parseInt(deler[3]));

                movieMap.put(deler[0], ny);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieMap;
    }

    public int getActorCount() {
        return adjGraf.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (Set<Edge> edges : adjGraf.values()) {
            count += edges.size();
        }
        return count/2;
    }
}
