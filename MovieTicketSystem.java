import java.util.*;

class MovieDetails {

    String genre;
    List<String> mvList;
    List<List<String>> showTimes;
    List<String> availableSeats;

    MovieDetails(String genre, List<String> mvList, List<List<String>> showTimes, List<String> availableSeats) {
        this.genre = genre;
        this.mvList = mvList;
        this.showTimes = showTimes;
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return genre;
    }
}

class CustomerInfo {

    String name;
    String movie;
    String times;
    String seats;

    CustomerInfo(String name, String movie, String times, String seats) {
        this.name = name;
        this.movie = movie;
        this.times = times;
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Movie: " + movie + "\n" +
                "Time: " + times + "\n" +
                "Seats: " + seats;
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

        while (true) {
            System.out.println();
            System.out.println("Genre List: ");
            System.out.println("-----------");
            for (int i = 0; i < gList.size(); i++) {
                System.out.println((i + 1) + ". " + gList.get(i));
            }

            System.out.println();

            System.out.print("Enter Genre Choice By Number: ");
            int userGenre = scanner.nextInt();
            scanner.nextLine();

            if (userGenre < 1 || userGenre > gList.size()) {
                System.out.println("Invalid Entry. Try Again.");
                return;
            }

            MovieDetails selectedGenre = gList.get(userGenre - 1);

            if (selectedGenre.genre.isEmpty()) {
                System.out.println();
                System.out.println("Empty Entry. Try Again.");
            } else {
                System.out.println();
                System.out.println(selectedGenre.genre + " " + "Movie List: ");
                System.out.println("--------------------");
                for (int i = 0; i < selectedGenre.mvList.size(); i++) {
                    System.out.println((i + 1) + ". " + selectedGenre.mvList.get(i));
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

            List<String> viewShows = selectedGenre.showTimes.get(userMovie - 1);

            System.out.println();
            System.out.println("Movie Times: ");
            System.out.println("--------------");
            for (int i = 0; i < viewShows.size(); i++) {
                System.out.println((i + 1) + ". " + viewShows.get(i));
            }

            System.out.println();
            System.out.print("Enter Movie Time By Number: ");
            int userTime = scanner.nextInt();
            scanner.nextLine();

            if (userTime < 1 || userTime > viewShows.size()) {
                System.out.println();
                System.out.println("Invalid Choice. Try Again.");
                return;
            }

            String selectedShowTime = viewShows.get(userTime - 1);

            if (selectedShowTime.isEmpty()) {
                System.out.println();
                System.out.println("Empty Entry. Try Again.");
                return;
            }

            System.out.println();
            System.out.println("Available Seats: ");
            System.out.println("-----------------");
            for (int i = 0; i < selectedGenre.availableSeats.size(); i++) {
                System.out.println((i+1) + ". " + selectedGenre.availableSeats.get(i));
            }

            System.out.println();
            System.out.print("Enter Chosen Seat By Number: ");
            int userSeat = scanner.nextInt();
            scanner.nextLine();

            String selectedSeat = selectedGenre.availableSeats.remove(userSeat - 1);

            if (userSeat < 1 || userSeat > selectedGenre.availableSeats.size()) {
                System.out.println("Invalid Choice. Try Again.");
            }

            if (selectedSeat.isEmpty()) {
                System.out.println("Empty Entry. Try Again.");
                return;
            }

            System.out.println();
            System.out.print("Enter Your Name: ");
            String userName = scanner.nextLine().trim();

            if (selectedMovie.isEmpty() || userName.isEmpty()) {
                System.out.println();
                System.out.println("Empty Entry. Try Again.");
            } else {
                System.out.println();
                cInfoList.add(new CustomerInfo(userName, selectedMovie, selectedShowTime, selectedSeat));
                System.out.print("Congratulations, " + userName + "! You Have Selected: " + selectedMovie + " (" + selectedGenre + ")!" + "\n" + "\n" +
                        "Add Another? (Y/N): ");
                char userYN = scanner.next().charAt(0);
                scanner.nextLine();

                if (userYN != 'Y') {
                    return;
                }
            }
        }
    }

    static void viewBookingInfo(List<CustomerInfo> cInfoList) {

        if (cInfoList.isEmpty()) {
            System.out.println();
            System.out.println("You Haven't Booked Any Movies Yet.");
        } else {
            System.out.println();
            System.out.println("Your Movie Booking Information: ");
            System.out.println("--------------------------------");
            for (int i = 0; i < cInfoList.size(); i++) {
                System.out.println((i+1) + ". " + cInfoList.get(i));
            }
        }
    }

    static void cancelBooking(List<CustomerInfo> cInfoList, Scanner scanner) {

        if (cInfoList.isEmpty()) {
            System.out.println();
            System.out.println("You Haven't Booked Any Movies Yet.");
        } else {
            viewBookingInfo(cInfoList);
            System.out.println();
            System.out.print("Enter Chosen Cancellation By Number: ");
            int userCancellation = scanner.nextInt();
            scanner.nextLine();
            CustomerInfo selectedCancel = cInfoList.remove(userCancellation - 1);
            System.out.println();
            System.out.println("You Have Successfully Canceled Booking: " + "\n" + selectedCancel);
        }

    }

    public static void main(String[] args) {
        List<MovieDetails> gList = new ArrayList<>();

        List<String> seatList = new ArrayList<>(Arrays.asList("A1", "A2", "A3", "A4", "A5"));

        List<String> seatListB = new ArrayList<>(Arrays.asList("B1", "B2", "B3", "B4", "B5"));

        List<String> seatListC = new ArrayList<>(Arrays.asList("C1", "C2", "C3", "C4", "C5"));

        gList.add(new MovieDetails("Horror", Arrays.asList("IT", "Halloween", "At Dawn"), Arrays.asList(
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM")
        ),
                seatList
        ));


        gList.add(new MovieDetails("Comedy", Arrays.asList("Dumb & Dumber", "Shazam!", "Brides Maids"), Arrays.asList(
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM")
        ),
                seatListB
                ));
        gList.add(new MovieDetails("Romance", Arrays.asList("Me Before You", "Our Story", "The Notebook"), Arrays.asList(
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM"),
                Arrays.asList("2PM-4PM", "6PM-8PM", "9PM-11PM")
        ),
                seatListC
        ));

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
                    cancelBooking(cInfoList, scanner);
                    break;
                case "5":
                    System.out.println();
                    System.out.println("Exited :)");
                    scanner.close();
                    return;
                default:
                    System.out.println();
                    System.out.println("Invalid Number Choice. Try Again.");
            }
        }
    }
}