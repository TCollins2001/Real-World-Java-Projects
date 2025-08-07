import java.util.Scanner;

class Bank {

    double userBalance;

    Bank(double userBalance) {
        this.userBalance = userBalance;
    }
}

public class BankAccount {

    static void viewBalance(Bank bank) {
        System.out.printf("Your Balance: $%.2f\n", bank.userBalance);
        System.out.println();
    }

    static void makeDeposit(Bank bank, Scanner scanner) {

        while (true) {
            System.out.println();

            System.out.print("Enter Deposit Amount (Using Two Decimal Places): ");
            double userDeposit = scanner.nextDouble();
            scanner.nextLine();

            if (userDeposit <= 0) {
                System.out.println("Invalid Entry. Try Again.");
            } else {
                bank.userBalance += userDeposit;

                System.out.println();

                System.out.println("Deposit Successful!");

                System.out.println();

                System.out.printf("New Balance: $%.2f\n", bank.userBalance);

                System.out.print("Do You Want To Add More? (Y/N): ");
                char userAddMore = scanner.next().trim().toUpperCase().charAt(0);
                scanner.nextLine();
                System.out.println();

                if (userAddMore != 'Y') {
                    break;
                }
            }
        }
    }

    static void makeWithdraw(Bank bank, Scanner scanner) {

        while (true) {
            System.out.println("Enter Withdrawal Amount (Using Two Decimal Places): ");
            double userWithdraw = scanner.nextDouble();
            scanner.nextLine();

            if (userWithdraw <= 0) {
                System.out.println("Invalid Entry. Try Again.");
            } else if (userWithdraw > bank.userBalance) {
                System.out.println("Overdraft. Try Again.");
            } else {
                bank.userBalance -= userWithdraw;
                System.out.println("Withdrawal Successful!");

                System.out.println();

                System.out.printf("New Balance: $%.2f\n", bank.userBalance);

                System.out.print("Do You Want To Withdraw More? (Y/N): ");
                char userWithdrawMore = scanner.next().trim().toUpperCase().charAt(0);
                scanner.nextLine();
                System.out.println();

                if (userWithdrawMore != 'Y') {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank(0.00);

       while (true) {

           System.out.println();

           System.out.println("1. View Balance");
           System.out.println("2. Add Deposit");
           System.out.println("3. Withdraw");
           System.out.println("4. Exit");

           System.out.println();

           System.out.print("Enter Number Of Choice: ");
           String userChoice = scanner.nextLine().trim();

           switch (userChoice) {
               case "1":
                   viewBalance(bank);
                   break;
               case "2":
                   makeDeposit(bank, scanner);
                   break;
               case "3":
                   makeWithdraw(bank, scanner);
                   break;
               case "4":
                   System.out.println("Exited :)");
                   scanner.close();
                   return;
               default:
                   System.out.println("Invalid Choice. Try Again.");
           }
       }
    }
}