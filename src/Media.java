// Main parent class of Films, VideoGames, Music Albums

public class Media {
    protected int id, releaseYear;
    protected String title;

    public Media(int id, int releaseYear, String title) { // all children will have these exact args
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
    }
    public int getId() {
        return id;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public String getTitle() {
        return title;
    }
}
