public class Movies extends Films {
    private int duration;

    public Movies (int id, String title, String director, String country, int releaseYear,
                   String rating, int duration, String description) {

        super(id, title, director, country, releaseYear, rating, description);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

}
