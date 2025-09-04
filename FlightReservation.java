import java.util.*;

class PassengerInfo {
    String name;
    String destination;
    Availabilities airline;
    boolean isConfirmed;

    PassengerInfo(String name, String destination, Availabilities airline) {
        this.name = name;
        this.destination = destination;
        this.airline = airline;
        this.isConfirmed = true;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Destination: " + destination + "\n" +
                "Airline: " + airline + "\n" +
                "Confirmed: " + (isConfirmed ? "Yes" : "No. You Have Been Waitlisted Until Flight Cancellation");
    }
}

class Availabilities {
    String airlineType;
    int numOfSeats;

    Availabilities(String airlineType, int numOfSeats) {
        this.airlineType = airlineType;
        this.numOfSeats = numOfSeats;
    }

    @Override
    public String toString() {
        return airlineType + " (Seats Available: " + numOfSeats + ")";
    }
}

class FlightReservation {

    static void bookFlight(List<PassengerInfo> pInfoList, List<Availabilities> avList, Scanner scanner) {
        System.out.println();
        System.out.print("Enter Name: ");
        String userName = scanner.nextLine().trim();
        System.out.println();
        System.out.print("Enter Destination: ");
        String userDestination = scanner.nextLine().trim();

        if (userName.isEmpty() || userDestination.isEmpty()) {
            System.out.println();
            System.out.println("Empty Entry. Try Again.");
            return;
        }

        viewFlights(avList);

        System.out.println();
        System.out.print("Choose Airline By Number: ");
        int userAirline = scanner.nextInt();
        scanner.nextLine();

        if (userAirline < 0 || userAirline > avList.size()) {
            System.out.println("Invalid Entry. Try Again.");
        }

        Availabilities passengerAirline = avList.get(userAirline - 1);

        PassengerInfo confirmedPassenger = new PassengerInfo(userName, userDestination, passengerAirline);

        if (passengerAirline.numOfSeats > 0) {
            passengerAirline.numOfSeats--;
            confirmedPassenger.isConfirmed = true;
            System.out.println();
            System.out.println("You Have Successfully Booked Your Flight!");
        } else {
            confirmedPassenger.isConfirmed = false;
            System.out.println();
            System.out.println("This Flight Is Full. You Have Been Waitlisted.");
        }

        pInfoList.add(confirmedPassenger);
    }

    static void viewFlights(List<Availabilities> avList) {
        System.out.println();
        System.out.println("Available Flights: ");
        System.out.println("-------------------");
        for (int i = 0; i < avList.size(); i++) {
            System.out.println((i+1) + ". " + avList.get(i));
        }
    }

    static void viewFlightInfo(List<PassengerInfo> pInfoList) {
        System.out.println();
        System.out.println("Your Flight Information: ");
        System.out.println("-------------------------");
        for (PassengerInfo p : pInfoList) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        List<PassengerInfo> pInfoList = new ArrayList<>();
        List<Availabilities> avList = new ArrayList<>();
        avList.add(new Availabilities("Delta", 2));
        avList.add(new Availabilities("American Airline", 5));
        avList.add(new Availabilities("Southwest", 9));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. Book Flight");
            System.out.println("2. View Available Flights");
            System.out.println("3. View Your Flight Information");
            System.out.println();
            System.out.print("Enter Choice By Number: ");
            String userChoice = scanner.nextLine().trim();

            switch(userChoice) {

                case "1":
                    bookFlight(pInfoList, avList, scanner);
                    break;
                case "2":
                    viewFlights(avList);
                    break;
                case "3":
                    viewFlightInfo(pInfoList);
                    break;
                default:
                    System.out.println("Invalid Choice. Try Again.");
            }
        }
    }
}