public class Media {
    protected int id, releaseYear;
    protected String title;

    public Media(int id, int releaseYear, String title) {
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
