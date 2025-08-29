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

    public static void main(String[] args) {
        List<PassengerInfo> pInfoList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
    }
}