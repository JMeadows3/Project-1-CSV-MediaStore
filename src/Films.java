public class Films extends Media{
    protected String director, country, rating, description;

    public Films(int id, String title, String director, String country, int releaseYear,
                  String rating, String description) {

        super(id, releaseYear, title);
        this.director = director;
        this.country = country;
        this.rating = rating;
        this.description = description;
    }

    public String getRating() {
        return rating;
    }
}
