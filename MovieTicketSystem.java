import java.util.*;

class Genre {
    String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return genre;
    }
}

class Movie {
    String title;

    Movie(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

class ShowTime {
    String startTime;
    int availableSeats;

    ShowTime(String startTime, int availableSeats) {
        this.startTime = startTime;
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return startTime + availableSeats;
    }
}

class MovieTicketSystem {

    static void selectGenre(List<Genre> gList, List<Movie> mvList, Scanner scanner) {
        System.out.println();
        System.out.println("Choose Genre: ");
        System.out.println("-------------");
        for (int i = 0; i < gList.size(); i++) {
            System.out.println((i+1) + ". " + gList.get(i));
        }

        System.out.println();

        System.out.print("Enter Genre Choice By Number: ");
        int userGenre = scanner.nextInt();
        scanner.nextLine();

        Genre selectedGenre = gList.get(userGenre - 1);

        if (selectedGenre.genre.isEmpty()) {
            System.out.println("Empty Entry. Try Again.");
        }

    }

    public static void main(String[]args) {
        List<Genre> gList = new ArrayList<>();
        gList.add(new Genre("Horror"));
        gList.add(new Genre("Comedy"));
        gList.add(new Genre("Romance"));

        List<Movie> mvList = new ArrayList<>();
        mvList.add(new Movie("Halloween"));
        mvList.add(new Movie("Dumb & Dumber"));
        mvList.add(new Movie("Me Before You"));

        Scanner scanner = new Scanner(System.in);

        selectGenre(gList, mvList, scanner);

    }
}