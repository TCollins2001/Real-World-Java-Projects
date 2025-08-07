import java.util.Scanner;

class Bank {

    double userBalance;

    Bank(double userBalance) {
        this.userBalance = userBalance;
    }
}

public class BankAccount {

    static void viewBalance (Bank bank) {
        System.out.printf("Your Balance: $%.2f", bank.userBalance);

    }

    static void makeDeposit(Bank bank, Scanner scanner) {

        while (true) {
            System.out.println();

            System.out.print("Enter Deposit: ");
            double userDeposit = scanner.nextDouble();

            if (userDeposit <= 0) {
                System.out.println("Invalid Entry. Try Again.");
            } else {
                bank.userBalance += userDeposit;

                System.out.println();

                System.out.println("Deposit Successful!");

                System.out.println();

                System.out.printf("New Balance: $%.2f", bank.userBalance);

                System.out.println();

                System.out.print("Do You Want To Add More? (Y/N) ");
                char useraddMore = scanner.next().trim().toUpperCase().charAt(0);
                System.out.println();

                if (useraddMore != 'Y') {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank(0.00);

       while (true) {
           System.out.println("1. View Balance");
           System.out.println("2. Add Deposit");
           System.out.println("3. Withdraw");
           System.out.println("4. Exit");

           System.out.println("Enter Number Of Choice: ");
           String userChoice = scanner.nextLine().trim();

           switch (userChoice) {
               case "1":
                   viewBalance(bank);
                   break;
               case "2":
                   makeDeposit(bank, scanner);
                   break;
               case "3":
               case "4":
           }
       }
    }
}