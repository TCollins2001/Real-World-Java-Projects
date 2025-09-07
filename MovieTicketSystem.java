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

    static void selectMovie(List<MovieDetails> gList, Scanner scanner) {
        System.out.println();
        System.out.println("Genre List: ");
        System.out.println("-----------");
        for (int i = 0; i < gList.size(); i++) {
            System.out.println((i+1) + ". " + gList.get(i));
        }

        System.out.println();

        System.out.print("Enter Genre Choice By Number: ");
        int userGenre = scanner.nextInt();
        scanner.nextLine();

        MovieDetails selectedGenre = gList.get(userGenre - 1);

        if (selectedGenre.genre.isEmpty()) {
            System.out.println();
            System.out.println("Empty Entry. Try Again.");
        } else {
            System.out.println();
            System.out.println("Movie List: ");
            System.out.println("-----------");
            for (int i = 0; i < selectedGenre.mvList.size(); i++) {
                System.out.println((i+1) + ". " + selectedGenre.mvList.get(i));
            }
        }
    }

    public static void main(String[] args) {
        List<MovieDetails> gList = new ArrayList<>();
        gList.add(new MovieDetails("Horror", Arrays.asList("IT", "Halloween", "At Dawn")));
        gList.add(new MovieDetails("Comedy", Arrays.asList("Dumb & Dumber", "Shazam!", "Brides Maids")));
        gList.add(new MovieDetails("Romance", Arrays.asList("Me Before You", "Our Story", "The Notebook")));

        Scanner scanner = new Scanner(System.in);

        selectMovie(gList, scanner);
    }
}