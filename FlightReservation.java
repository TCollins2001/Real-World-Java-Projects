import java.util.*;

class PassengerInfo {

    String name;
    String destination;

    PassengerInfo(String name, String destination) {
        this.name = name;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Destination: " + destination;
    }
}

class FlightReservation {

    static void bookFlight(List<PassengerInfo> pInfoList, Scanner scanner) {
        System.out.println();
        System.out.print("Enter Passenger Name: ");
        String userName = scanner.nextLine().trim();
        System.out.println();
        System.out.print("Enter Destination: ");
        String userDestination = scanner.nextLine().trim();

        PassengerInfo newPassenger = new PassengerInfo(userName, userDestination);
        if (userName.isEmpty() || userDestination.isEmpty()) {
            System.out.println("Empty Entry. Try Again.");
        } else {
            pInfoList.add(newPassenger);
            System.out.println();
            System.out.println("Flight Booked Successfully!");
        }
    }

    public static void main(String[] args) {
        List<PassengerInfo> pInfoList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        bookFlight(pInfoList, scanner);
    }
}