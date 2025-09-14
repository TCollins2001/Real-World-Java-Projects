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
        return name + " (Qty: " + quantity;
    }
}

class GroceryListApp {
    public static void main(String[] args) {

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
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
            }
        }
    }
}
