import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class grafbygger {
    public static void main(String[] args) {

        String ActorsPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig3\\marvel_actors.tsv";
        File Actors = new File(ActorsPath);

        String MoviesPath = "C:\\Users\\IsakF\\Documents\\VScode\\IN2010\\IN2010  gruppe\\IN2010_Oblig1\\Oblig3\\marvel_movies.tsv";
        File Movies = new File(MoviesPath);

        ArrayList<Node> skuespillere = les_og_bygg_Actors(Actors); //liste med alle skuespillere, som node objekter
        ArrayList<Edge> filmer = les_og_bygg_Movies(Movies);

    }

    public grafbygger() {
    }

    public static ArrayList<Edge> les_og_bygg_Movies(File fil) {
        ArrayList<Edge> filmer = new ArrayList<Edge>();

        try (BufferedReader br = new BufferedReader(new FileReader(fil))) {
            String linje;

            while ((linje = br.readLine()) != null) {
                String[] deler = linje.split("\t");
                Edge ny = new Edge(deler[0], deler[1], Float.parseFloat(deler[2]), Integer.parseInt(deler[3]));

                filmer.add(ny);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmer;
    }

    public static ArrayList<Node> les_og_bygg_Actors(File fil) {
        ArrayList<Node> skuespillere = new ArrayList<Node>();

        try (BufferedReader br = new BufferedReader(new FileReader(fil))) {
            String linje;

            while ((linje = br.readLine()) != null) {
                String[] deler = linje.split("\t");
                Node ny = new Node(deler[0], deler[1]);

                for (int i = 2; i < deler.length; i++) { //skal kjøre for hvor mange tt-ider hver skuespiller har
                    ny.addMovie(deler[i]);
                }
                skuespillere.add(ny);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skuespillere;
    }
}