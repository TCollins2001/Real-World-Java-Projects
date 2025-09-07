import java.util.*;

class MovieDetails {

    String genre;
    List<String> mvList;

    MovieDetails(String genre, List<String> mvList) {
        this.genre = genre;
        this.mvList = mvList;
    }

    @Override
    public String toString() {
        return genre;
    }
}

class MovieTicketSystem {

    public static void main(String[] args) {
        List<MovieDetails> gList = new ArrayList<>();
        gList.add(new MovieDetails("Horror", Arrays.asList("IT", "Halloween", "At Dawn")));
        gList.add(new MovieDetails("Comedy", Arrays.asList("Dumb & Dumber", "Shazam!", "Brides Maids")));
        gList.add(new MovieDetails("Romance", Arrays.asList("Me Before You", "Our Story", "The Notebook")));
    }
}