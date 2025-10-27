public class Edge {
    private Actor toActor;
    private String movieId;
    private float rating;

    public Edge(Actor toActor, String movieId, float rating) {
        this.toActor = toActor;
        this.movieId = movieId;
        this.rating = rating;
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

    @Override
    public String toString() {
        return "==[ " + movieId + " (" + rating + ") ]==>";
    }
}
