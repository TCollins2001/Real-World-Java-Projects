import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome To The Simple Calculator!");

        System.out.println();

        System.out.println("Enter First Number: ");
        int userInputOne = scanner.nextInt();

        System.out.println();

        System.out.println("Enter First Number: ");
        int userInputTwo = scanner.nextInt();

        System.out.println("Enter +, -, *, or /: ");
        String operator = scanner.next();
    }
}
