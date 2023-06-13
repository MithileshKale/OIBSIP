import java.util.Scanner;

public class ATM {
    private User currentUser;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        // Prompt for user ID and PIN
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter User PIN: ");
        String pin = scanner.nextLine();

        // Authenticate user
        User user = authenticateUser(userId, pin);

        if (user != null) {
            currentUser = user;
            System.out.println("\nLogin Successful!");
            showMainMenu();
        } else {
            System.out.println("\nInvalid User ID or PIN. Login Failed!");
        }
    }

    private User authenticateUser(String userId, String pin) {
        
        if (userId.equals("123456") && pin.equals("1234")) {
            return new User("Mithilesh Kale", userId, pin);
        }
        return null;
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. View Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("\nThank you for using the ATM!");
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {
        System.out.println("\nTransactions History:");
        for (Transaction transaction : currentUser.getTransactions()) {
            System.out.println(transaction);
        }
    }

    private void performWithdrawal() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter withdrawal amount: ");
        double amount = scanner.nextDouble();

        if (currentUser.getAccount().withdraw(amount)) {
            Transaction transaction = new Transaction("Withdrawal", -amount);
            currentUser.addTransaction(transaction);
            System.out.println("\nWithdrawal successful!");
        } else {
            System.out.println("\nInsufficient funds or invalid amount. Withdrawal failed!");
        }
    }

    private void performDeposit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter deposit amount: ");
        double amount = scanner.nextDouble();

        currentUser.getAccount().deposit(amount);
        Transaction transaction = new Transaction("Deposit", amount);
        currentUser.addTransaction(transaction);

        System.out.println("\nDeposit successful!");
    }

    private void performTransfer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter recipient's user ID: ");
        String recipientId = scanner.nextLine();

        User recipient = findUserById(recipientId);

        if (recipient != null) {
            System.out.print("Enter transfer amount: ");
            double amount = scanner.nextDouble();

            if (currentUser.getAccount().transfer(recipient.getAccount(), amount)) {
                Transaction transaction = new Transaction("Transfer to " + recipient.getName(), -amount);
                currentUser.addTransaction(transaction);
                recipient.addTransaction(new Transaction("Transfer from " + currentUser.getName(), amount));
                System.out.println("\nTransfer successful!");
            } else {
                System.out.println("\nInsufficient funds or invalid amount. Transfer failed!");
            }
        } else {
            System.out.println("\nRecipient user not found. Transfer failed!");
        }
    }

    private User findUserById(String userId) {
        
        if (userId.equals("123456")) {
            return new User("Mithilesh Kale", userId, "5678");
        }
        return null;
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
