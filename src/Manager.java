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

    public void getData ( String file ) throws IOException {

           try (BufferedReader br = new BufferedReader(new FileReader(file))) { //
            String line;

            while ((line = br.readLine()) != null) { // makes each line in the file a string and not null
                if (!line.trim().isEmpty()) { // removes the white space at start and end of the line and skips if its empty
                    ++counter;
                    //mediaList.add(line);
                    String[] data = line.split(","); // temp array to split the line by , to parse through easier
                    Media media = parseData(data); // makes an obj of media from parseData method
                    if (media != null) {
                        mediaList.add(media); // if it's not null then it adds the ArrayList mediaList
                    }
                }
            }
        }
    }

// ========================================== getCount Method ==========================================================

    public int getCount() {
        return counter;
    } // returns the amount of products in the file

// ========================================== parseData Method =========================================================
// =========================(checks for type then sets it to the correct method for parsing)============================

    private Media parseData(String[] data) {

        try {
            String type = data[1];  // get the type, to correctly parse the data

            return switch (type) { // switch case to call the method that data[1]'s type is -> points to the method
                case "Movie" -> movieData(data);
                case "TV Show" -> tvShowData(data);
                case "Music Album" -> musicData(data);
                case "Video Game" -> gamesData(data);
                default -> null;
            };
        }catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

// ========================================== Splitting parseData by (,), Methods ======================================
//==========( All of these Methods parse the data, making them int, String or doubles depending on the class called)====

    private Movies movieData(String[] data) {
        try {
            return new Movies(
                    Integer.parseInt(data[0]),
                    data[2], data[3], data[4],
                    Integer.parseInt(data[5]),
                    data[6],
                    Integer.parseInt(data[7]),
                    data[8]
            );
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private TVShows tvShowData(String[] data) {

            try{
            return new TVShows (
                    Integer.parseInt(data[0]),
                    data[2], data[3], data[4],
                    Integer.parseInt(data[5]),
                    data[6], data[7], data[8]
            );
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private MusicAlbums musicData(String[] data) {

        try{
            return new MusicAlbums(
                    Integer.parseInt(data[0]),
                    Integer.parseInt(data[2]),
                    data[3], data[4],
                    Integer.parseInt(data[5]),
                    Integer.parseInt(data[6]),
                    Double.parseDouble(data[7]),
                    data[8]
            );
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
            }
    }

    private VideoGames gamesData(String[] data) {

        try{
            return new VideoGames(
                    Integer.parseInt(data[0]),
                    data[2], data[3],
                    Integer.parseInt(data[4]),
                    data[5], data[6],
                    Double.parseDouble(data[7])
            );
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
            }
    }

//========================================== numMediaType ==============================================================
//====================( checks amount of movies, games.. or whatever the argument calls )===============================

    public int numMediaType(Class mediaType) { // method to reduce redundancy by using the class to get the number of items
        int amount = 0;

        for (Media item : mediaList) { // checks all items in media list
            if (mediaType.isInstance(item)) { // if the item ( class shows,movies,games...) in media return the amount of time the items in arraylist
                ++amount;
            }
        }
        return amount;
    }

//========================================== shortest duration =========================================================
//==============================( get the shortest movie or music albums title)=========================================

    public String shortestDuration(Class mediaType) { //
        int intShort = Integer.MAX_VALUE;
        double dubShort = Double.MAX_VALUE;
        String name = null;

        for (Media item : mediaList) { // checks all items in arraylist (medialist) for Medias type
            if (mediaType.isInstance(item)) { // checks if arg is a type of Media

                if (item instanceof Movies) { // if items in Movies, make a Obj
                    Movies movies = (Movies) item;

                    if (movies.getDuration() < intShort) { // loops through all movie duration for the shortest movie
                        intShort = movies.getDuration();
                        name = movies.getTitle();
                        // intShort and name save the movie with the shortest duration each loop through
                    }
                    // below is the same as above BUT if its part of MusicAlbums
                } else if (item instanceof MusicAlbums) { //
                    MusicAlbums albums = (MusicAlbums) item;

                    if (albums.getDuration() < dubShort) {
                        dubShort = albums.getDuration();
                        name = albums.getTitle();
                    }
                }
            }
        }
        return name; // return the name of the shortest Music Album or Movie
    }

// ========================================= avgRating Method ==========================================================
// ===============================( get avg TV Shows and Movies rating )================================================

    public String avgRating() {
        // list get an index size by calling numMediaType and adding TVshows and Movies for a correct index size
        String[] list = new String[ numMediaType(Movies.class) + numMediaType(TVShows.class) ];
        int index = 0; // used to increase index when adding to list

        for (Media item : mediaList) { // loops through and checks items in mediaList arrayList
            if (item instanceof Films) { // if in Films ( movies and tvshows are children of Films )
                Films films = (Films) item; // make films the object of Films --> (Films)
                list[index] = films.getRating(); //adds the rating of every TVshow and Movie
                ++index;
            }
        }

        String avgRating = null; // get the name of the most common counter
        int mostCommon = 0; // hold the current highest count

        for (int i = 0; i < list.length; ++i) {
            String rating = list[i];
            int counter = 0; // keep track of the current count in the loop

            for (int j = 0;  j < list.length; ++j ) {
                if (rating.equals(list[j])) {
                    counter++; //adds 1 to counter everytime it finds a match
                }
            }
            if (counter > mostCommon) {
                mostCommon = counter; // if the counter is larger than mostCommons, then mostCommon changes to counters count
                avgRating = rating; // acts the same way but saves the name of the highest counter
            }
        }
        return avgRating; // returns the avg age rating between movies and shows
    }

// ========================================= mostPopular Method =======================================================
//==========================(Gets most Popular Music Album / Video Game by Sales)===============================================

    public String mostPopular(Class mediaType) { // MusicAlbum.class or VideoGames.class for argument

        double mostGSold = 0; // most Games sold as games is a double ( G is for VideoGames )
        int mostMSold = 0;  // most music albums sold as its an int ( M is for Music Albums
        String name = null; // savs the name of the most popular video game or album

        for (Media item : mediaList) {
            if (mediaType.isInstance(item)) {

                if (item instanceof MusicAlbums) {
                    MusicAlbums albums = (MusicAlbums) item; // makes a Music album obj

                    if (albums.getSales() > mostMSold) {
                        mostMSold = albums.getSales(); // saves the most amount sold to check if the next albums sold is larger
                        name = albums.getTitle(); // saves the title of the album with most sales
                    }

                } else if (item instanceof VideoGames) {
                    VideoGames games = (VideoGames) item; // makes a Video game obj

                    if (games.getSales() > mostGSold) {
                        mostGSold = games.getSales(); // saves the most amount sold to check if the next games sold is larger
                        name = games.getTitle(); // saves the title of the game with most sales
                    }
                }
            }
        }
        return name; // return the game or albums title
    }

//========================================== oldestProduct Method ======================================================
//====================================(Find the oldest product available)===============================================

    public String oldestProduct() {
        int age = Integer.MAX_VALUE;
        String name = null;

        for (Media item : mediaList) {
            if (item.getReleaseYear() < age ) { // checks for the smallest release year in Arraylist
                age = item.getReleaseYear(); // saves the oldest product year to age
                name = item.getTitle(); // saves the oldest products name to name
            }
        }
        return name; // return the oldest products name
    }
//========================================== Return Arraylist Method ===================================================

    public ArrayList<Media> getMediaList() {
        return mediaList; // return the arraylist its self
    }
}
