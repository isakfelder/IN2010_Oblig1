import java.util.ArrayList;

public class Actor { //denne er for actors
    private String id;
    private String name;
    private ArrayList<String> movies;

    public Actor(String id, String name) {
        this.id = id;
        this.name = name;
        this.movies = new ArrayList<>();
    }

    public void addMovie(String movieId) {
        movies.add(movieId);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "Actor name: " + name;
    }

    /* 
    @Override
    public String toString() {
        return "Name: " + name + ", id: " + id + ", filmer: " + movies;
    }*/
}
