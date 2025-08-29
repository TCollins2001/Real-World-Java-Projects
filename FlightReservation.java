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

    static void viewFlightInfo(List<PassengerInfo> pInfoList) {
        System.out.println();
        if (pInfoList.isEmpty()) {
            System.out.println("No Flights Booked Yet!");
        } else {
            for (PassengerInfo p : pInfoList) {
                System.out.println("Flight Information: ");
                System.out.println("---------------------");
                System.out.println(p);
            }
        }
    }

    public static void main(String[] args) {
        List<PassengerInfo> pInfoList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. Book Flight");
            System.out.println("2. View Flight Information");
            System.out.println();
            System.out.print("Enter Choice By Number: ");
            String userChoice = scanner.nextLine().trim();

            switch (userChoice) {
                case "1":
                    bookFlight(pInfoList, scanner);
                    break;
                case "2":
                    viewFlightInfo(pInfoList);
                    break;
                default:
                    System.out.println("Invalid Choice. Try Again.");
            }
        }
    }
}