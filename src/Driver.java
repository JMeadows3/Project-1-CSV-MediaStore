//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Driver {
    public static void main(String[] args){
        Manager manager = new Manager();
        manager.getData("resources/project1dataset.csv");
        System.out.println(manager.numMediaType(Movies.class) + " MOVIES"); // add arguments **********
        System.out.println(manager.numMediaType(TVShows.class) + " TV SHOWS");
        System.out.println(manager.numMediaType(VideoGames.class) + " Video Games");
        System.out.println(manager.numMediaType(MusicAlbums.class) + " Music Albums");
        System.out.println("\"" + manager.shortestDuration(MusicAlbums.class) + "\"" + " is the Shortest album");
        System.out.println("\"" + manager.shortestDuration(Movies.class) + "\"" + " it the Shortest movies");
        System.out.println(manager.printCount() + " Products");
        System.out.println("\"" + manager.avgRating() + "\"" + " is the Avg Film rating");
        System.out.println("\"" + manager.mostPopular(VideoGames.class) + "\"" + " is the Most Popular Video Game");
        System.out.println("\"" + manager.mostPopular(MusicAlbums.class) + "\"" + " is the Most Popular Music Album");
        System.out.println("\"" + manager.oldestProduct() + "\"" + " is the Oldest Product");

        //System.out.println(manager.getMediaList());
    }
}
