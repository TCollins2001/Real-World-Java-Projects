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
    List<Item> item;

    OrderItem(String category, List<Item> item) {
        this.category = category;
        this.item = item;
    }
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

        List<OrderItem> itemList = new ArrayList<>();

        itemList.add(new OrderItem("Food", Arrays.asList(
                new Item("Burger", 7.00),
                new Item("Chicken Tenders", 5.00),
                new Item("French Fries", 3.00)
        )));

        itemList.add(new OrderItem("Drinks", Arrays.asList(
                new Item("Pepsi", 2.00),
                new Item("Coke", 2.00),
                new Item("Lemonade", 1.50)
        )));

        itemList.add(new OrderItem("Desserts", Arrays.asList(
                new Item("Chocolate Chip Cookies", 3.00),
                new Item("Banana Split", 5.00),
                new Item("Cinnamon Rolls", 3.00)
        )));

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
