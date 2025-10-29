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

public class grafbygger {
    private Map<Actor, Set<Edge>> adjGraf;

    public static void main(String[] args) {

        String ActorsPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig3\\marvel_actors.tsv";
        File ActorsFil = new File(ActorsPath);

        String MoviesPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig3\\marvel_movies.tsv";
        File MoviesFil = new File(MoviesPath);

        grafbygger g = new grafbygger();

        ArrayList<Actor> actors = les_og_bygg_Actors(ActorsFil);
        Map<String, Movie> movieMap = les_og_bygg_Movies(MoviesFil);

        g.byggGraf(actors, movieMap);

        System.out.println(g.adjGraf);

        System.out.println(g.getActorCount());
        System.out.println(g.getEdgeCount());
    }

    public grafbygger() {
        this.adjGraf = new HashMap<>();
    }

    public void addEdge(Actor actor1, Actor actor2, String movieId, float rating) {
        addActor(actor1);
        addActor(actor2);

        Edge edge = new Edge(actor2, movieId, rating);
        adjGraf.get(actor1).add(edge);

        Edge tilbakeEdge = new Edge(actor1, movieId, rating);
        adjGraf.get(actor2).add(tilbakeEdge);
    }

    public void addActor(Actor actor) {
        adjGraf.putIfAbsent(actor, new HashSet<Edge>());
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
            float rating = film.getRating();
            
            for (int i = 0; i < skuespillere.size(); i++) {
                for (int j = i + 1; j < skuespillere.size(); j++) {
                    Actor a1 = skuespillere.get(i);
                    Actor a2 = skuespillere.get(j);

                    adjGraf.get(a1).add(new Edge(a2, filmId, rating));
                    adjGraf.get(a2).add(new Edge(a1, filmId, rating));
                }
            }
        }
        //return adjGraf;
    }

    public static ArrayList<Actor> les_og_bygg_Actors(File fil) {
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
        return count / 2;
    }
}
