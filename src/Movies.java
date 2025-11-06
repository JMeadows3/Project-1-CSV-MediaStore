public class Movies extends Films {
    private int duration;

    public Movies (int id, String title, String director, String country, int releaseYear,
                   String rating, int duration, String description) { // all args related to Movies

        super(id, title, director, country, releaseYear, rating, description); // from Parent class Films
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

}
