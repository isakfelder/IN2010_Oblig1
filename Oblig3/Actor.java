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

    //prøvd med og uten dette endrer ikke utfallet (noe chat sa)
    /*
     @Override
public boolean equals(Object o) {
    if (this == o) return true;              // Samme objekt
    if (o == null || getClass() != o.getClass()) return false;
    Actor actor = (Actor) o;
    return id.equals(actor.id);              // Sammenlign på ID
}

@Override
public int hashCode() {
    return id.hashCode();                    // Bruk samme felt som i equals
} 
    */


    /* 
    @Override
    public String toString() {
        return "Name: " + name + ", id: " + id + ", filmer: " + movies;
    }*/
}
