// id,type,release_year,artist,title,global_sales,tracks,duration_(minutes),genre
public class MusicAlbums extends Media {
    private String artist, genre;
    private int sales, tracks;
    private double duration;

    public MusicAlbums(int id, int releaseYear, String artist, String title,
                 int sales, int tracks, double duration, String genre) {

        super(id, releaseYear, title);
        this.artist = artist;
        this.sales = sales;
        this.tracks = tracks;
        this.duration = duration;
        this.genre = genre;
    }

    public int getSales() {
        return sales;
    }

    public double getDuration() {
        return duration;
    }
}
