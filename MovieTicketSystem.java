import java.util.*;

class MovieDetails {

    String genre;
    List<String> mvList;
    List<List<String>> showTimes;

    MovieDetails(String genre, List<String> mvList, List<List<String>> showTimes) {
        this.genre = genre;
        this.mvList = mvList;
        this.showTimes = showTimes;
    }

    @Override
    public String toString() {
        return genre;
    }
}

class CustomerInfo {

    String name;
    String movie;

    CustomerInfo(String name, String movie) {
        this.name = name;
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Movie: " + movie;
    }
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

    static void selectMovie(List<MovieDetails> gList, List<CustomerInfo> cInfoList, Scanner scanner) {
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

        if (userMovie < 1 || userMovie > selectedGenre.mvList.size()) {
            System.out.println();
            System.out.println("Invalid Choice. Try Again.");
            return;
        }

        String selectedMovie = selectedGenre.mvList.get(userMovie - 1);

        List<String> selectedShow = selectedGenre.showTimes.get(userMovie - 1);

        System.out.println();
        System.out.println("Movie Times: ");
        System.out.println("--------------");
        for (int i = 0; i < selectedShow.size(); i++) {
            System.out.println((i+1) + ". " + selectedShow.get(i));
        }

        System.out.println();
        System.out.print("Enter Movie Time By Number: ");
        int userTime = scanner.nextInt();
        scanner.nextLine();

        System.out.println();
        System.out.print("Enter Your Name: ");
        String userName = scanner.nextLine().trim();

        if (selectedMovie.isEmpty() || userName.isEmpty()) {
            System.out.println();
            System.out.println("Empty Entry. Try Again.");
        } else {
            System.out.println();
            cInfoList.add(new CustomerInfo(userName, selectedMovie));
            System.out.println("Congratulations " + userName + "! You Have Selected: " + selectedMovie + " (" + selectedGenre + ")!" + "\n" + "\n" +
                    "Are You Sure?");
        }
    }

    static void viewBookingInfo(List<CustomerInfo> cInfoList) {
        System.out.println();
        System.out.println("Your Movie Booking Information: ");
        System.out.println("--------------------------------");
        for (CustomerInfo c : cInfoList) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
        List<MovieDetails> gList = new ArrayList<>();
        gList.add(new MovieDetails("Horror", Arrays.asList("IT", "Halloween", "At Dawn"), Arrays.asList(
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM")
        )));


        gList.add(new MovieDetails("Comedy", Arrays.asList("Dumb & Dumber", "Shazam!", "Brides Maids"), Arrays.asList(
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM")
        )));
        gList.add(new MovieDetails("Romance", Arrays.asList("Me Before You", "Our Story", "The Notebook"), Arrays.asList(
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM")
        )));

        List<CustomerInfo> cInfoList = new ArrayList<>();

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
                    selectMovie(gList, cInfoList, scanner);
                    break;
                case "3":
                    viewBookingInfo(cInfoList);
                    break;
                case "4":
                    break;
                case "5":
                    System.out.println();
                    System.out.println("Exited :)");
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid Number Choice. Try Again.");
            }
        }
    }
}