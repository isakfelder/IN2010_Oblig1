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

        String ActorsPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\Oblig1\\IN2010_Oblig1\\Oblig3\\marvel_actors.tsv";
        File ActorsFil = new File(ActorsPath);

        String MoviesPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\Oblig1\\IN2010_Oblig1\\Oblig3\\marvel_movies.tsv";
        File MoviesFil = new File(MoviesPath);

        grafbygger g = new grafbygger();

        ArrayList<Actor> actors = les_og_bygg_Actors(ActorsFil);
        ArrayList<Movie> movies = les_og_bygg_Movies(MoviesFil);

        g.byggGraf(actors, movies);
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
        adjGraf.putIfAbsent(actor, new HashSet<>());
    }

    public Map<Actor, Set<Edge>> byggGraf(ArrayList<Actor> actors, ArrayList<Movie> movies) {
        

        return adjGraf;
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

    public static ArrayList<Movie> les_og_bygg_Movies(File fil) {
        ArrayList<Movie> movies = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fil))) {
            String linje;

            while ((linje = br.readLine()) != null) {
                String[] deler = linje.split("\t");
                Movie ny = new Movie(deler[0], deler[1], Float.parseFloat(deler[2]), Integer.parseInt(deler[3]));

                movies.add(ny);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
