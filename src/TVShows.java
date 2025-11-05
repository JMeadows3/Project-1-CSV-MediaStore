
public class TVShows extends Films{
    private String seasons;

    public TVShows (int id, String title, String director, String country, int releaseYear,
                   String rating, String seasons, String description) {

        super(id, title, director, country, releaseYear, rating, description);
        this.seasons = seasons;
    }

    public String getSeasons() {
        return seasons;
    }
}
