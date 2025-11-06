
public class TVShows extends Films{
    private String seasons;

    public TVShows (int id, String title, String director, String country, int releaseYear,
                   String rating, String seasons, String description) { // all args related to TVShows

        super(id, title, director, country, releaseYear, rating, description); // from parent class Films
        this.seasons = seasons;
    }

    public String getSeasons() {
        return seasons;
    }
}
