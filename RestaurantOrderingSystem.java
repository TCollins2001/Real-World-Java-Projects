import java.util.*;

class CustomerInformation {

    String name;
    String contact;
    List<Item> items;

    CustomerInformation(String name, String contact, List<Item> items) {
        this.name = name;
        this.contact = contact;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Contact: " + contact + "\n" +
                "Ordered Items: " + items + "\n";
    }
}

class OrderItem {

    String category;
    List<Item> item;

    OrderItem(String category, List<Item> item) {
        this.category = category;
        this.item = item;
    }

    @Override
    public String toString() {
        return category;
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

    static List<Item> selectedItems = new ArrayList<>();

    static void orderNow(List<CustomerInformation> cInfoList, List<OrderItem> itemList, Scanner scanner) {

        while (true) {
            System.out.println();
            System.out.println("Choose Category: ");
            System.out.println("-----------------");
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println((i + 1) + ". " + itemList.get(i));
            }

            System.out.println();
            System.out.print("Enter Category Number: ");
            int userCategory = scanner.nextInt();
            scanner.nextLine();

            if (userCategory < 1 || userCategory > itemList.size()) {
                System.out.println();
                System.out.println("Invalid Choice. Try Again.");
                return;
            }

            OrderItem selectedCategory = itemList.get(userCategory - 1);

            System.out.println();
            System.out.println("List Of " + selectedCategory);
            System.out.println("--------------------------------");
            for (int i = 0; i < selectedCategory.item.size(); i++) {
                System.out.println((i + 1) + ". " + selectedCategory.item.get(i));
            }

            System.out.println();
            System.out.print("Enter Item Number: ");
            int userItem = scanner.nextInt();
            scanner.nextLine();

            if (userItem < 1 || userItem > selectedCategory.item.size()) {
                System.out.println();
                System.out.println("Invalid Choice. Try Again.");
                return;
            }

            Item selectedItem = selectedCategory.item.get(userItem - 1);
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
        System.out.print("Enter Contact: ");
        String userContact = scanner.nextLine().trim();

        if (userName.isEmpty() || userContact.isEmpty()) {
            System.out.println();
            System.out.println("Invalid Choice. Try Again.");
        } else {
            System.out.println();
            System.out.println("Congratulations " + userName + "! You Have Successfully Made Your Order!");
        }

        cInfoList.add(new CustomerInformation(userName, userContact, selectedItems));

    }



    static void reviewOrder(List<CustomerInformation> cInfoList) {
        if (cInfoList.isEmpty()) {
            System.out.println();
            System.out.println("No Orders Yet.");
        } else {
            System.out.println();
            System.out.println("Order Review: ");
            System.out.println("---------------------");
            for (CustomerInformation c : cInfoList) {
                System.out.println(c);
            }
        }
    }

    static void submitOrder(Scanner scanner) {
        if (selectedItems.isEmpty()) {
            System.out.println();
            System.out.println("No Orders Yet.");
        } else {
            System.out.println();
            System.out.println("Your Order Summary: ");
            System.out.println("---------------------");
            for (int i = 0; i < selectedItems.size(); i++) {
                System.out.println((i + 1) + ". " + selectedItems.get(i));
            }

            System.out.println();
            System.out.print("Would You Like To Submit Order, Cancel Order, or Remove Items? (S/C/R): ");
            char userSOR = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            switch (userSOR) {
                case 'S':
                    System.out.println();
                    double total = 0;
                    for (Item i : selectedItems) {
                        total += i.price;
                    }
                    System.out.println("Your Total: $" + String.format("%.2f", total));
                    System.out.println();
                    System.out.println("Submitted! Thank You!");
                    break;
                case 'C':
                    System.out.println();
                    break;
                case 'R':
                    System.out.println();
                    System.out.print("Enter Item Number To Cancel: ");
                    int userRemove = scanner.nextInt();
                    scanner.nextLine();

                    if (userRemove < 1 || userRemove > selectedItems.size()) {
                        System.out.println();
                        System.out.println("Invalid Choice. Try Again.");
                    } else {
                        System.out.println();
                        Item selectedCancel = selectedItems.remove(userRemove - 1);
                        System.out.println(selectedCancel + " Successfully Canceled!");
                    }
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid Choice. Try Again.");
            }

        }
    }

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
                    orderNow(cInfoList, itemList, scanner);
                    break;
                case "2":
                    reviewOrder(cInfoList);
                    break;
                case "3":
                    submitOrder(scanner);
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
