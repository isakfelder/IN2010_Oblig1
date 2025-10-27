public class Movie {
    private String id;
    private String name;
    private float rating;
    private int stemmer; 

    public Movie(String id, String name, float rating, int stemmer) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.stemmer = stemmer;
    }

    public String getId() {
        return id;
    } 

    public String getName() {
        return name;
    } 

    public float getRating() {
        return rating;
    } 

    public int getStemmer() {
        return stemmer;
    }

    public String toString() {
        return "id: " + id + ", name: " + name + ", rating: " + rating + ", stemmer: " + stemmer;
    }
}
