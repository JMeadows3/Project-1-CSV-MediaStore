
// Parent class of TVShows and Movies

public class Films extends Media{
    protected String director, country, rating, description;

    public Films(int id, String title, String director, String country, int releaseYear,
                  String rating, String description) { // all items related of Films

        super(id, releaseYear, title); // comes from its Parent class Media
        this.director = director;
        this.country = country;
        this.rating = rating;
        this.description = description;
    }

    public String getRating() {
        return rating;
    }
}
