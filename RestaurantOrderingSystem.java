import java.util.*;

class CustomerInformation {

    String name;
    String contact;
    OrderItem cate;
    String item;

    CustomerInformation(String name, String contact, String item) {
        this.name = name;
        this.contact = contact;
        this.item = item;
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\n" +
                "Contact Number: " + "\n" +
                "Ordered Items: " + item;
    }
}

class OrderItem {

    String category;
    List<String> itemList;

    OrderItem(String category,  List<String> itemList) {
        this.category = category;
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return category;
    }
}

class RestaurantOrderingSystem {

    static void orderItems(List<OrderItem> orderItemList, List<CustomerInformation> customerInfoList, Scanner scanner) {

        List<String> selectedItems = new ArrayList<>();

        while (true) {
            System.out.println();
            System.out.println("Categories: ");
            System.out.println("-------------");
            for (int i = 0; i < orderItemList.size(); i++) {
                System.out.println((i + 1) + ". " + orderItemList.get(i));
            }
            System.out.println();
            System.out.print("Choose Category By Number: ");
            int userCategory = scanner.nextInt();
            scanner.nextLine();

            if (userCategory < 1 || userCategory > orderItemList.size()) {
                System.out.println();
                System.out.println("Invalid Choice. Try Again.");
                return;
            }

            OrderItem selectedCategory = orderItemList.get(userCategory - 1);

            System.out.println();
            System.out.println("List Of " + selectedCategory);
            System.out.println("-----------------");
            for (int i = 0; i < selectedCategory.itemList.size(); i++) {
                System.out.println((i + 1) + ". " + selectedCategory.itemList.get(i));
            }
            System.out.println();
            System.out.print("Choose Item By Number: ");
            int userItem = scanner.nextInt();
            scanner.nextLine();

            if (userItem < 1 || userItem > selectedCategory.itemList.size()) {
                System.out.println();
                System.out.println("Invalid Choice. Try Again.");
                return;
            }

            String selectedItem = selectedCategory.itemList.get(userItem - 1);
            selectedItems.add(selectedItem);

            System.out.println();
            System.out.print("Add Another Item? (Y/N): ");
            char userYN = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            if (userYN != 'Y') {
                break;
            }
        }

        System.out.println();
        System.out.print("Enter Name: ");
        String userName = scanner.nextLine().trim();
        System.out.println();
        System.out.print("Enter Contact Number: ");
        String userNumber = scanner.nextLine().trim();

        if (userName.isEmpty() || userNumber.isEmpty()) {
            System.out.println();
            System.out.println("Empty Entry. Try Again.");
            return;
        }

        customerInfoList.add(new CustomerInformation(userName, userNumber, String.join(", ", selectedItems)));

    }

    static void reviewOrder(List<CustomerInformation> customerInfoList) {

        if (customerInfoList.isEmpty()) {
            System.out.println("No Orders Yet.");
        } else {
            System.out.println();
            System.out.println("Your Order Summary: ");
            System.out.println("--------------------");
            for (int i = 0; i < customerInfoList.size(); i++) {
                System.out.println((i + 1) + ". " + customerInfoList.get(i));
            }
        }
    }

    public static void main(String[] args) {

        List<OrderItem> orderItemList = new ArrayList<>();

        List<CustomerInformation> customerInfoList = new ArrayList<>();

        orderItemList.add(new OrderItem("Drinks", Arrays.asList("Pepsi", "Coke", "Lemonade")));
        orderItemList.add(new OrderItem("Food", Arrays.asList("Burger", "Chicken Tenders", "French Fries")));
        orderItemList.add(new OrderItem("Desserts", Arrays.asList("Chocolate Chip Cookies", "Banana Split", "Cinnamon Rolls")));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. Order Now");
            System.out.println("2. Review Order");
            System.out.println("3. Submit Order");
            System.out.println("4. Exit Menu");
            System.out.println();
            System.out.print("Enter Number Choice: ");
            String userChoice = scanner.nextLine().trim();

            switch (userChoice) {
                case "1":
                    orderItems(orderItemList, customerInfoList, scanner);
                    break;
                case "2":
                    reviewOrder(customerInfoList);
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