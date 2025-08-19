import java.util.*;

class RoomManagement {

    int roomNum;
    String roomType;
    String pricePerNight;
    boolean isAvailable;

    RoomManagement(int roomNum, String roomType, String pricePerNight) {

        this.roomNum = roomNum;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return roomNum + " (" + roomType + ")" + " - " + pricePerNight + " " +
                (isAvailable ? "[Available]" : "[Unavailable]");
    }
}

public class HotelBooking {

    static void viewRooms(List<RoomManagement> roomList) {
        System.out.println();
        System.out.println("List Of Rooms: ");
        System.out.println("---------------");
        for (int i = 0; i < roomList.size(); i++) {
            System.out.println((i+1) + ". Room " + roomList.get(i));
        }
    }

    static void bookRooms(List<RoomManagement> roomList, Scanner scanner) {

        while (true) {
            viewRooms(roomList);
            System.out.println();
            System.out.print("Enter Number Choice Of Room: ");
            int userRoomChoice = scanner.nextInt();
            System.out.println();

            if (userRoomChoice < 1 || userRoomChoice > roomList.size()) {
                System.out.println("Invalid Choice. Try Again.");
                return;
            }

            RoomManagement selectedRoom = roomList.get(userRoomChoice - 1);

            if (selectedRoom.isAvailable) {
                selectedRoom.isAvailable = false;
                System.out.println("Congratulations! You've Booked Room " + selectedRoom.roomNum + "!");
            } else {
                System.out.println("Room Already Booked And Currently Unavailable!");
            }

            System.out.println();
            System.out.print("Do You Wanna Book Another? (Y/N): ");
            char userYN = scanner.next().trim().toUpperCase().charAt(0);
            scanner.nextLine();

            if (userYN != 'Y') {
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<RoomManagement> roomList = new ArrayList<>();
        roomList.add(new RoomManagement(100, "Single", "$100"));
        roomList.add(new RoomManagement(101, "Single", "$100"));
        roomList.add(new RoomManagement(102, "Double", "$150"));
        roomList.add(new RoomManagement(103, "Suite", "$250"));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. View Rooms");
            System.out.println("2. Book Rooms");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Enter Number Choice: ");
            String userChoice = scanner.nextLine().trim();

            switch (userChoice) {
                case "1":
                    viewRooms(roomList);
                    break;
                case "2":
                    bookRooms(roomList, scanner);
                    break;
                case "3":
                    System.out.println();
                    System.out.println("Exited :)");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Number Choice. Try Again.");
            }
        }
    }
}

