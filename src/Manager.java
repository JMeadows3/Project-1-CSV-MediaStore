import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Manager {
    private ArrayList<Media> mediaList;
    private int counter;

    public Manager() {
        mediaList = new ArrayList<>();
    }

// ============================================= getData Method ========================================================
// ======================================( reading file to parse data )=================================================

    public void getData ( String file ) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while( (line = br.readLine()) != null ) {
                if(!line.trim().isEmpty()) {
                    ++counter;
                    //mediaList.add(line);
                    String[] data = line.split(",");
                    Media media = parseData(data);
                    if (media != null) {
                        mediaList.add(media);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int printCount() {
        return counter;
    }
// =============================================== Parsing Methods =====================================================

    private Media parseData(String[] data) {
        String type = data[1];

        return switch (type) {
            case "Movie" -> movieData(data);
            case "TV Show" -> tvShowData(data);
            case "Music Album" -> musicData(data);
            case "Video Game" -> gamesData(data);
            default -> null;
        };
    }

// ============================================== Splitting parseData by (,) methods ===================================

    private Movies movieData(String[] data) {
        return new Movies (
                Integer.parseInt(data[0]),
                data[2], data[3], data[4],
                Integer.parseInt(data[5]),
                data[6],
                Integer.parseInt(data[7]),
                data[8]
        );
    }

    private TVShows tvShowData(String[] data) {
        return new TVShows (
                Integer.parseInt(data[0]),
                data[2], data[3], data[4],
                Integer.parseInt(data[5]),
                data[6], data[7], data[8]
        );
    }

    private MusicAlbums musicData(String[] data) {
        return new MusicAlbums(
                Integer.parseInt(data[0]),
                Integer.parseInt(data[2]),
                data[3], data[4],
                Integer.parseInt(data[5]),
                Integer.parseInt(data[6]),
                Double.parseDouble(data[7]),
                data[8]
        );
    }

    private VideoGames gamesData(String[] data) {
        return new VideoGames(
                Integer.parseInt(data[0]),
                data[2], data[3],
                Integer.parseInt(data[4]),
                data[5], data[6],
                Double.parseDouble(data[7])
        );
    }

    // ================================================= numMediaType ==================================================
    // ============================= checks amount of movies, games.. or whatever the argument calls ===================

    public int numMediaType(Class mediaType) { // method to reduce redundancy by using the class to get the number of items
        int amount = 0;

        for (Media item : mediaList) { // checks all items in media list
            if (mediaType.isInstance(item)) { // if the item ( class shows,movies,games...) in media return the amount of time the items in arraylist
                ++amount;
            }
        }
        return amount;
    }

//====================================================== shortest duration ==============================================

    public String shortestDuration(Class mediaType) { //
        int intShort = Integer.MAX_VALUE;
        double dubShort = Double.MAX_VALUE;
        String name = null;

        for (Media item : mediaList) {
            if (mediaType.isInstance(item)) {

                if (item instanceof Movies) {
                    Movies movies = (Movies) item;

                    if (movies.getDuration() < intShort) {
                        intShort = movies.getDuration();
                        name = movies.getTitle();
                    }

                } else if (item instanceof MusicAlbums) { // change to music album
                    MusicAlbums albums = (MusicAlbums) item;

                    if (albums.getDuration() < dubShort) {
                        dubShort = albums.getDuration();
                        name = albums.getTitle();
                    }
                }
            }
        }
        return name;
    }

// ========================================= avgRating Method ==========================================================
// ===============================( get avg TV Shows and Movies rating )================================================

    public String avgRating() {
        String[] list = new String[ numMediaType(Movies.class) + numMediaType(TVShows.class) ];
        int index = 0;

        for (Media item : mediaList) {
            if (item instanceof Films) {
                Films films = (Films) item;
                list[index] = films.getRating();
                ++index;
            }
        }

        String avgRating = null;
        int counter1 = 0;

        for (int i = 0; i < list.length; ++i) {
            String rating = list[i];
            int counter2 = 0;

            for (int j = 0;  j < list.length; ++j ) {
                if (rating.equals(list[j])) {
                    counter2++;
                }
            }
            if (counter2 > counter1) {
                counter1 = counter2;
                avgRating = rating;
            }
        }
        return avgRating;
    }

// ========================================= mostPopular Method =======================================================
//==========================(Gets most Popular Music Album / Video Game by Sales)===============================================

    public String mostPopular(Class mediaType) { // MusicAlbum.class or VideoGames.class for argument

        double mostGSold = 0;
        int mostMSold = 0;
        String name = null;

        for (Media item : mediaList) {
            if (mediaType.isInstance(item)) {

                if (item instanceof MusicAlbums) {
                    MusicAlbums albums = (MusicAlbums) item;

                    if (albums.getSales() > mostMSold) {
                        mostMSold = albums.getSales();
                        name = albums.getTitle();
                    }

                } else if (item instanceof VideoGames) {
                    VideoGames games = (VideoGames) item;

                    if (games.getSales() > mostGSold) {
                        mostGSold = games.getSales();
                        name = games.getTitle();
                    }
                }
            }
        }
        return name;
    }

//========================================== oldestProduct Method ======================================================
//====================================(Find the oldest product available)===============================================

    public String oldestProduct() {
        int age = Integer.MAX_VALUE;
        String name = null;

        for (Media item : mediaList) {
            if (item.getReleaseYear() < age ) {
                age = item.getReleaseYear();
                name = item.getTitle();
            }
        }
        return name;
    }
//========================================== Return Arraylist Method ===================================================

    public ArrayList<Media> getMediaList() {
        return mediaList;
    }
}
