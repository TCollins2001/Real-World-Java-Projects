import java.util.*;

class FoodItem {
    String name;
    int quantity;
    boolean isBought;

    FoodItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.isBought = false;
    }

    @Override
    public String toString() {
        return name + " - " + "Qty: " + quantity + " " + "(" + (isBought? "Bought" : "Not Bought") + ")";
    }
}

    class GroceryListApp {

        static void viewList(Scanner scanner, List<FoodItem> foodItemList, boolean allowMarking) {
            System.out.println();
            if (foodItemList.isEmpty()) {
                System.out.println("No Items Yet.");
            } else {
                System.out.println("Grocery List: ");
                System.out.println("--------------");
                for (int i = 0; i < foodItemList.size(); i++) {
                    System.out.println((i + 1) + ". " + foodItemList.get(i));
                }
            }

            if (allowMarking) {
                System.out.println();
                System.out.print("Would You Like To Mark Any Items Bought? (Y/N): ");
                char userYNB = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine();

                if (userYNB != 'Y') {
                    return;
                } else {
                    System.out.println();
                    System.out.print("Enter Number Of Item To Be Marked: ");
                    int userMark = scanner.nextInt();
                    scanner.nextLine();

                    FoodItem selectedMark = foodItemList.get(userMark - 1);

                    if (userMark < 1 || userMark > foodItemList.size()) {
                        System.out.println();
                        System.out.println("Invalid Entry. Try Again.");
                    } else {
                        selectedMark.isBought = true;
                    }

                }
            }
        }

        static void addItem(Scanner scanner, List<FoodItem> foodItemList) {

            while (true) {
                System.out.println();
                System.out.print("Add Item: ");
                String userItem = scanner.nextLine().trim();

                System.out.println();
                System.out.print("Quantity: ");
                int userQuantity = scanner.nextInt();

                scanner.nextLine();

                if (userItem.isEmpty() || userQuantity <= 0) {
                    System.out.println();
                    System.out.println("Invalid. Try Again.");
                } else {
                    foodItemList.add(new FoodItem(userItem, userQuantity));
                }

                System.out.println();
                System.out.print("Would You Like To Add Another Item? (Y/N): ");
                char userYNA = scanner.next().toUpperCase().charAt(0);
                scanner.nextLine();

                if (userYNA != 'Y') {
                    return;
                }
            }

        }

        static void removeItem(Scanner scanner, List<FoodItem> foodItemList) {
            if (foodItemList.isEmpty()) {
                    System.out.println();
                    System.out.println("No Items Yet.");
                } else {
                    viewList(scanner, foodItemList, false);
                    System.out.println();
                    System.out.print("Item To Remove: ");
                    int userRemoved = scanner.nextInt();

                    scanner.nextLine();

                    FoodItem selectedRemove;

                    if (userRemoved <= 0) {
                        System.out.println();
                        System.out.println("Invalid Entry. Try Again.");
                    } else {
                        selectedRemove = foodItemList.remove(userRemoved - 1);
                        System.out.println();
                        System.out.println(selectedRemove.name + " " + "Removed!");
                    }

                    System.out.println();
                    System.out.print("Would You Like To Remove Another Item? (Y/N): ");
                    char userYNR = scanner.next().toUpperCase().charAt(0);
                    scanner.nextLine();

                    if (userYNR != 'Y') {
                        return;
                    }
                }
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<FoodItem> foodItemList = new ArrayList<>();

            while (true) {
                System.out.println();
                System.out.println("1. View Grocery List");
                System.out.println("2. Add Item");
                System.out.println("3. Remove Item");
                System.out.println("4. Exit");

                System.out.println();

                System.out.print("Enter Number Choice: ");
                String userChoice = scanner.nextLine().trim();

                switch (userChoice) {
                    case "1":
                        viewList(scanner, foodItemList, true);
                        break;
                    case "2":
                        addItem(scanner, foodItemList);
                        break;
                    case "3":
                        removeItem(scanner, foodItemList);
                        break;
                    case "4":
                        System.out.println();
                        System.out.println("Exited :) ");
                        return;
                    default:
                        System.out.println();
                        System.out.println("Invalid Choice. Try Again.");
                }

            }
        }
}