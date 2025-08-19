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

class BookManagement {
    String name;
    RoomManagement room;
    String checkIn;
    String checkOut;


    BookManagement(String name, RoomManagement room, String checkIn, String checkOut) {
        this.name = name;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Guest: " + name + "\n" +
                "Room: " + room + "\n" +
                "Check-In Date: " + checkIn + "\n" +
                "Check-Out Date: " + checkOut + "\n";

    }
}

public class HotelBooking {

    static void bookRooms(List<RoomManagement> roomList, List<BookManagement> bookingList, Scanner scanner) {

        while (true) {
            System.out.println();
            System.out.print("Enter Guest Name: ");
            String userName = scanner.nextLine().trim();
            System.out.println();
            System.out.print("Enter Check-In Date (mm/dd/yyyy): ");
            String userCheckIn = scanner.nextLine().trim();
            System.out.println();
            System.out.print("Enter Check-Out Date (mm/dd/yyyy): ");
            String userCheckOut = scanner.nextLine().trim();
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
                bookingList.add(new BookManagement(userName, selectedRoom, userCheckIn, userCheckOut));
                System.out.println("Congratulations " + userName + "! " + "You've Booked Room " + selectedRoom.roomNum + "!");
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

    static void cancelBookings(List<BookManagement> bookingList, Scanner scanner) {

        if (bookingList.isEmpty()) {
            System.out.println();
            System.out.println("No Bookings To Cancel.");
            return;
        }

        viewBookings(bookingList);
        System.out.println();
        System.out.print("Enter Number Of The Booking You Want To Cancel: ");
        int userCancelChoice = scanner.nextInt();
        scanner.nextLine();

        if (userCancelChoice < 1 || userCancelChoice > bookingList.size()) {
            System.out.println("Invalid Entry. Try Again.");
        } else {
            System.out.println();
            BookManagement canceledRoom = bookingList.remove(userCancelChoice - 1);
            canceledRoom.room.isAvailable = true;
            System.out.println("You've Successfully Canceled Your Booking For: " + "\n" + "\n" + canceledRoom);
        }
    }

    static void viewRooms(List<RoomManagement> roomList) {
        System.out.println();
        System.out.println("List Of Rooms: ");
        System.out.println("---------------");
        for (int i = 0; i < roomList.size(); i++) {
            System.out.println((i+1) + ". Room " + roomList.get(i));
        }
    }

    static void viewBookings(List<BookManagement> bookingList) {
        if (bookingList.isEmpty()) {
            System.out.println();
            System.out.println("No Bookings Yet.");
        } else {
            System.out.println();
            System.out.println("Your Bookings: ");
            System.out.println("---------------");
            for (int i = 0; i < bookingList.size(); i++) {
                System.out.println((i + 1) + ". " + bookingList.get(i));
            }
        }
    }

    public static void main(String[] args) {
        List<RoomManagement> roomList = new ArrayList<>();
        List<BookManagement> bookingList = new ArrayList<>();
        roomList.add(new RoomManagement(100, "Single", "$100"));
        roomList.add(new RoomManagement(101, "Single", "$100"));
        roomList.add(new RoomManagement(102, "Double", "$150"));
        roomList.add(new RoomManagement(103, "Suite", "$250"));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. Book Rooms");
            System.out.println("2. Cancel Bookings");
            System.out.println("3. View Bookings");
            System.out.println("4. View Rooms");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter Number Choice: ");
            String userChoice = scanner.nextLine().trim();

            switch (userChoice) {
                case "1":
                    bookRooms(roomList, bookingList, scanner);
                    break;
                case "2":
                    cancelBookings(bookingList, scanner);
                    break;
                case "3":
                    viewBookings(bookingList);
                    break;
                case "4":
                    viewRooms(roomList);
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

