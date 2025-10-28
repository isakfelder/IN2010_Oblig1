import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.StringJoiner;

public class grafbygger {

    public static void main(String[] args) {

        String ActorsPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig3\\marvel_actors.tsv";
        File Actors = new File(ActorsPath);

        String MoviesPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig3\\marvel_movies.tsv";
        File Movies = new File(MoviesPath);

        grafbygger g = new grafbygger();

        Map<String, Node> actorMap = les_og_bygg_Actors(Actors);
        Map<String, Edge> movieMap = les_og_bygg_Movies(Movies);

        //System.out.println("Filmer: " + g.antallFilmer());
        //System.out.println("Skuespillere: " + g.antallSkuespillere());

        Map<Node, List<Node>> graf = g.byggGraf(actorMap, movieMap);
        System.out.println("Bygde graf med " + graf.size() + " noder.");

        // Skriv ut grafen (adjacency list)
        skrivUtGraf(graf);

        // Skriv ut filmer -> skuespillere
        skrivUtFilmTilSkuespillere(actorMap, movieMap);


    }

    public grafbygger() {

    }

    // Skriver ut grafen som en adjacency-liste: hver node etterfulgt av sine naboer
    public static void skrivUtGraf(Map<Node, List<Node>> graf) {
        for (Map.Entry<Node, List<Node>> entry : graf.entrySet()) {
            Node node = entry.getKey();
            List<Node> naboer = entry.getValue();

            StringJoiner sj = new StringJoiner(", ");
            for (Node n : naboer) {
                sj.add(n.getName() + " (" + n.getId() + ")");
            }

            System.out.println(node.getName() + " (" + node.getId() + ") -> [" + sj.toString() + "]");
        }
    }

    // Skriver ut for hver film (id, navn) lista av skuespillere som er med i filmen
    public static void skrivUtFilmTilSkuespillere(Map<String, Node> actorMap, Map<String, Edge> movieMap) {
        // Bygg map fra filmId -> liste over Node (skuespillere)
        Map<String, List<Node>> filmTilSkuespillere = new HashMap<>();
        for (Node actor : actorMap.values()) {
            for (String filmId : actor.getMovies()) {
                filmTilSkuespillere.computeIfAbsent(filmId, k -> new ArrayList<>()).add(actor);
            }
        }

        // Skriv ut hver film med navn (hvis kjent) og skuespillere
        for (Map.Entry<String, List<Node>> entry : filmTilSkuespillere.entrySet()) {
            String filmId = entry.getKey();
            Edge film = movieMap.get(filmId);
            String filmNavn = (film != null) ? film.getName() : "(ukjent tittel)";

            StringJoiner sj = new StringJoiner(", ");
            for (Node n : entry.getValue()) {
                sj.add(n.getName() + " (" + n.getId() + ")");
            }

            System.out.println(filmNavn + " [" + filmId + "] -> [" + sj.toString() + "]");
        }
    }

    public Map<Node, List<Node>> byggGraf(Map<String, Node> actorMap, Map<String, Edge> movieMap) {
        Map<Node, List<Node>> graf = new HashMap<>();

        //film med alle skuespillere som har spilt i den
        Map<String, List<Node>> filmTilSkuespillere = new HashMap<>();
        
        //gå gjennom alle skuespillere og legg de til i filmTilSkuespillere
        for (Node actor : actorMap.values()) {
            for (String filmId : actor.getMovies()) {
                filmTilSkuespillere.computeIfAbsent(filmId, k -> new ArrayList<>()).add(actor);
            }
        }

        //for hver film, koble sammen alle skuespillere i den filmen
        for (List<Node> cast : filmTilSkuespillere.values()) {
            for (int i = 0; i < cast.size(); i++) {
                Node a = cast.get(i);

                for (int j = i + 1; j < cast.size(); j++) {
                    Node b = cast.get(j);

                    graf.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
                    graf.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
                }
            }
        }
        return graf;
    }

    public static Map<String, Edge> les_og_bygg_Movies(File fil) {
        Map<String, Edge> movieMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fil))) {
            String linje;

            while ((linje = br.readLine()) != null) {
                String[] deler = linje.split("\t");
                Edge ny = new Edge(deler[0], deler[1], Float.parseFloat(deler[2]), Integer.parseInt(deler[3]));

                movieMap.put(deler[0], ny);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieMap;
    }

    public static Map<String, Node> les_og_bygg_Actors(File fil) {
        Map<String, Node> actorMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fil))) {
            String linje;

            while ((linje = br.readLine()) != null) {
                String[] deler = linje.split("\t");
                Node ny = new Node(deler[0], deler[1]);

                for (int i = 2; i < deler.length; i++) { //skal kjøre for hvor mange tt-ider hver skuespiller har
                    ny.addMovie(deler[i]);
                }
                actorMap.put(deler[0], ny);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actorMap;
    }
}