public class Edge {
    private Actor  actor_1;
    private Actor actor_2;
    private String movieId;
    private float rating;
    private String film_name;

    public Edge(Actor actor_1, Actor actor_2, String movieId, float rating, String film_name) {
        this.actor_1 = actor_1;
        this.actor_2 = actor_2;
        this.movieId = movieId;
        this.rating = rating;
        this.film_name = film_name;
    }

    public Actor getToActor(Actor fra) {
        if (fra == actor_1){return actor_2;}
        else if(fra == actor_2){return actor_1;}
        else{return null;}
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
        return "Edge: " + movieId + " (" + rating + ") to actor: " + actor_1;
    }
}
