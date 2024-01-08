import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Amount: " + amount;
    }
}

class Account {
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Account() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
        }
    }

    public void transfer(Account targetAccount, double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            targetAccount.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + targetAccount, amount));
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

class ATM {
    private Account account;

    public ATM(Account account) {
        this.account = account;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Enter target account number: ");
                    int targetAccountNumber = scanner.nextInt();
                    // For simplicity, assuming a single account for the example
                    Account targetAccount = new Account();
                    account.transfer(targetAccount, transferAmount);
                    break;
                case 4:
                    account.displayTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}

public class task1 {
    public static void main(String[] args) {
        // Create an account
        Account userAccount = new Account();

        // Create an ATM for the account
        ATM atm = new ATM(userAccount);

        // Start the ATM interface
        atm.start();
    }
}
