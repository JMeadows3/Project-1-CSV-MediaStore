
//  id,type,title,platform,release_year,genre,publisher,copies_sold_(millions)
public class VideoGames extends Media {
        private String platform, genre, publisher;
        private double sales;

        public VideoGames(int id, String title, String platform, int releaseYear,
                     String genre, String publisher, double sales) { // all args related to VideoGames

            super(id, releaseYear, title); // comes from its Parent class Media
            this.platform = platform;
            this.genre = genre;
            this.publisher = publisher;
            this.sales = sales;
        }

        public double getSales() { // in the millions
            return sales;
        }

}
