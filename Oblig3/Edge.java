public class Edge { //denne er for filmer
    private String id;
    private String name;
    private float rating;
    private int stemmer; 

    public Edge(String id, String name, float rating, int stemmer) {
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

    //en dobbelt peker som kan gå begge veier med hensyn ti vilken node man putter inn. hva tenker du. 
    //vet ikke helt hvordan vi skal gjøre det med idene i string og sånt men ja noe sånt
    // må definere nodene når den lages men vil ikke endre mange steder før du ser.
    /* 
    public Node andreEnden(Node node) {
        if (node.getId().equals(node1.getId())) {
            return node2.id;
        } else if (node.getId().equals(node2.getId())) {
            return node1.id;
        } else {
            return null;
        }
    }*/
}