import java.util.*;

class CustomerInformation {

    String name;
    String contact;

    CustomerInformation(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Contact: " + contact + "\n";
    }
}

class OrderItem {

    String category;

}

class Item {

    String itemName;
    double price;

    Item(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    @Override
    public String toString() {
        return itemName + " ($" + String.format("%.2f", price) + ")";
    }
}

class RestaurantOrderingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<CustomerInformation> cInfoList = new ArrayList<>();

        while (true) {
            System.out.println();
            System.out.println("1. Order Now");
            System.out.println("2. Review Order");
            System.out.println("3. Submit Order");
            System.out.println("4. Exit Menu");
            System.out.println();
            System.out.print("Enter Number Choice: ");
            String userChoice = scanner.nextLine().trim();

            switch(userChoice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid Choice. Try Again.");
            }

        }

    }
}
