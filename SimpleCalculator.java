import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome To The Simple Calculator!");

        System.out.println();

        System.out.print("Enter First Number: ");
        int userInputOne = scanner.nextInt();

        System.out.println();

        System.out.print("Enter Second Number: ");
        int userInputTwo = scanner.nextInt();

        System.out.println();

        System.out.print("Enter +, -, *, or /: ");
        String operator = scanner.next();

        System.out.println();

        switch (operator) {
            case "+":
                System.out.println("= " + (userInputOne + userInputTwo));
                break;
            case "-":
                System.out.println("= " + (userInputOne - userInputTwo));
                break;
            case "*":
                System.out.println("= " + (userInputOne * userInputTwo));
                break;
            case "/":
                System.out.println("= " + (userInputOne / userInputTwo));
                break;
            default:
                System.out.println("Invalid Input. No Result Available.");
        }
    }
}
