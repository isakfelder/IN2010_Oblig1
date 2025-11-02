public class Edge {
    private Actor toActor;
    private String movieId;
    private float rating;
    private String film_name;

    public Edge(Actor toActor, String movieId, float rating, String film_name) {
        this.toActor = toActor;
        this.movieId = movieId;
        this.rating = rating;
        this.film_name = film_name;
    }

    public Actor getToActor() {
        return toActor;
    }

    public String getMovieId() {
        return movieId;
    }

    public float getRating() {
        return rating;
    }

    public String getFilm_Navn(){
        return film_name;
    }

    @Override
    public String toString() {
        return "Edge: " + movieId + " (" + rating + ") to actor: " + toActor;
    }
}
