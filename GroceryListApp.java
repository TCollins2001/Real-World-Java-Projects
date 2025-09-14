import java.util.*;

class FoodItem {
    String name;
    int quantity;

    FoodItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " (Qty: " + quantity + ")";
    }
}

class GroceryListApp {

    static void viewList(List<FoodItem> foodItemsList) {

        if (foodItemsList.isEmpty()) {
            System.out.println();
            System.out.println("No Items Yet.");
        } else {
            System.out.println();
            System.out.println("Grocery List: ");
            System.out.println("---------------");
            for (int i = 0; i < foodItemsList.size(); i++) {
                System.out.println((i + 1) + ". " + foodItemsList.get(i));
            }
        }
    }

    static void addItem(List<FoodItem> foodItemsList, Scanner scanner) {
        System.out.println();
        System.out.print("Add Item: ");
        String userItem = scanner.nextLine().trim();
        System.out.println();
        System.out.print("Enter Quantity: ");
        int userQuantity = scanner.nextInt();
        scanner.nextLine();


        if (userItem.isEmpty()) {
            System.out.println();
            System.out.println("Empty Entry. Try Again.");
            return;
        }

        if (userQuantity < 1) {
            System.out.println();
            System.out.println("Invalid Quantity. Try Again.");
            return;
        }

        foodItemsList.add(new FoodItem(userItem, userQuantity));

        System.out.println();
        System.out.println("Added Item To List!");

    }


    public static void main(String[] args) {

        List<FoodItem> foodItemsList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. View Full List");
            System.out.println("2. Add Items");
            System.out.println("3. Remove Items");
            System.out.println("4. Exit");
            System.out.println();
            System.out.print("Enter Number Choice: ");
            String userChoice = scanner.nextLine().trim();

            switch (userChoice) {
                case "1":
                    viewList(foodItemsList);
                    break;
                case "2":
                    addItem(foodItemsList, scanner);
                    break;
                case "3":
                    break;
                case "4":
                    System.out.println();
                    System.out.println("Exited :)");
                    return;
                default:
                    System.out.println();
                    System.out.println("Invalid Choice. Try Again.");
            }
        }
    }
}
