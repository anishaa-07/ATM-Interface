import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String userId;
    private String pin;
    private double balance;

    User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class ATMOperations {

    private User user;
    private ArrayList<String> history = new ArrayList<>();

    ATMOperations(User user) {
        this.user = user;
    }

    void deposit(double amount) {
        user.setBalance(user.getBalance() + amount);
        history.add("Deposited: ₹" + amount);
        System.out.println("✅ Amount deposited successfully");
    }

    void withdraw(double amount) {
        if (amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            history.add("Withdrawn: ₹" + amount);
            System.out.println("💵 Please collect your cash");
        } else {
            System.out.println("❌ Insufficient balance");
        }
    }

    void transfer(double amount) {
        if (amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            history.add("Transferred: ₹" + amount);
            System.out.println("✅ Amount transferred successfully");
        } else {
            System.out.println("❌ Insufficient balance");
        }
    }

    void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet");
        } else {
            System.out.println("📄 Transaction History:");
            for (String h : history) {
                System.out.println(h);
            }
        }
    }
}

public class ATM {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        User user = new User("1234", "0000", 10000);
        ATMOperations atm = new ATMOperations(user);

        System.out.println("🏧 Welcome to ATM");

        System.out.print("Enter User ID: ");
        String uid = sc.next();

        System.out.print("Enter PIN: ");
        String pin = sc.next();

        if (uid.equals(user.getUserId()) && pin.equals(user.getPin())) {

            int choice;
            do {
                System.out.println("\n1. Transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        atm.showHistory();
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        atm.withdraw(sc.nextDouble());
                        break;

                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        atm.deposit(sc.nextDouble());
                        break;

                    case 4:
                        System.out.print("Enter amount to transfer: ");
                        atm.transfer(sc.nextDouble());
                        break;

                    case 5:
                        System.out.println("🙏 Thank you for using ATM");
                        break;

                    default:
                        System.out.println("❌ Invalid choice");
                }

            } while (choice != 5);

        } else {
            System.out.println("❌ Invalid User ID or PIN");
        }

        sc.close();
    }
}
