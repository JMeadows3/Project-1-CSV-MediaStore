import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Driver {
    public static void main(String[] args){

        try { // creates manager obj, to load file and print analytics While checking for IOExceptions
            Manager manager = new Manager();
            manager.getData("resources/project1dataset.csv");
            printAnalytics(manager);

        } catch (IOException e) {
            System.out.println("Error: No file Found ->" + e.getMessage());
        }
    }
//======================================= printAnalytics Method ========================================================

    public static void printAnalytics(Manager manager) { //print method to clean up main

        System.out.println(manager.numMediaType(Movies.class) + " MOVIES"); // number of movies
        System.out.println(manager.numMediaType(TVShows.class) + " TV SHOWS"); // number of TV Shows
        System.out.println(manager.numMediaType(VideoGames.class) + " Video Games"); // number of Video Games
        System.out.println(manager.numMediaType(MusicAlbums.class) + " Music Albums"); // number of Music Albums
        System.out.println("\"" + manager.shortestDuration(MusicAlbums.class) + "\"" + " is the Shortest album"); // shortest Music Album
        System.out.println("\"" + manager.shortestDuration(Movies.class) + "\"" + " it the Shortest movies"); // shortest Movie
        System.out.println(manager.getCount() + " Products"); // number of product in the file
        System.out.println("\"" + manager.avgRating() + "\"" + " is the Avg Film rating"); // avg age rating of Movies/Shows
        System.out.println("\"" + manager.mostPopular(VideoGames.class) + "\"" + " is the Most Popular Video Game"); // Most sold Video Game
        System.out.println("\"" + manager.mostPopular(MusicAlbums.class) + "\"" + " is the Most Popular Music Album"); // Most Sold Music Album
        System.out.println("\"" + manager.oldestProduct() + "\"" + " is the Oldest Product"); // Oldest overall Product
    }
}