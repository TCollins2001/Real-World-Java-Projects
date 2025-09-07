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

class ShowTime {

    String times;
    int availableSeats;
}

class CustomerInfo {

    String name;
    int numOfTickets;
    boolean isConfirmed;
}

class MovieTicketSystem {

    static void viewSelection(List<MovieDetails> gList) {
        System.out.println();
        System.out.println("Full Movie List: ");
        System.out.println("--------------------------------------------------");
        for (MovieDetails m : gList) {
            System.out.println(m + ": " + "\n" + m.mvList);
        }
    }

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
            System.out.println(selectedGenre.genre + " " + "Movie List: ");
            System.out.println("--------------------");
            for (int i = 0; i < selectedGenre.mvList.size(); i++) {
                System.out.println((i+1) + ". " + selectedGenre.mvList.get(i));
            }
        }

        System.out.println();
        System.out.print("Enter Movie Choice By Number: ");
        int userMovie = scanner.nextInt();
        scanner.nextLine();

        String selectedMovie = selectedGenre.mvList.get(userMovie - 1);

        if (selectedMovie.isEmpty()) {
            System.out.println();
            System.out.println("Empty Entry. Try Again.");
        } else {
            System.out.println();
            System.out.println("You Have Selected: " + selectedMovie + " (" + selectedGenre + ")");
        }
    }

    public static void main(String[] args) {
        List<MovieDetails> gList = new ArrayList<>();
        gList.add(new MovieDetails("Horror", Arrays.asList("IT", "Halloween", "At Dawn")));
        gList.add(new MovieDetails("Comedy", Arrays.asList("Dumb & Dumber", "Shazam!", "Brides Maids")));
        gList.add(new MovieDetails("Romance", Arrays.asList("Me Before You", "Our Story", "The Notebook")));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. View Movie Selection");
            System.out.println("2. Book Movie Tickets");
            System.out.println("3. View Booking Information");
            System.out.println("4. Cancel Movie Booking");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter Choice By Number: ");
            String userChoice = scanner.nextLine().trim();

            switch (userChoice) {
                case "1":
                    viewSelection(gList);
                    break;
                case "2":
                    selectMovie(gList, scanner);
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Invalid Number Choice. Try Again.");
            }
        }
    }
}