import java.util.*;

class Management {

    int roomNum;
    String roomType;
    String pricePerNight;
    boolean isAvailable;

    Management(int roomNum, String roomType, String pricePerNight) {

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

    static void viewRooms(List<Management> roomList) {
        System.out.println();
        System.out.println("List Of Rooms: ");
        System.out.println("---------------");
        for (int i = 0; i < roomList.size(); i++) {
            System.out.println((i+1) + ". Room " + roomList.get(i));
        }
    }

    public static void main(String[] args) {
        List<Management> roomList = new ArrayList<>();
        roomList.add(new Management(100, "Single", "$100"));
        roomList.add(new Management(101, "Single", "$100"));
        roomList.add(new Management(102, "Double", "$150"));
        roomList.add(new Management(103, "Suite", "$250"));
        Scanner scanner = new Scanner(System.in);

        viewRooms(roomList);
    }
}

