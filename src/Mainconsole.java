import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mainconsole {
    private static final List<User> users = new ArrayList<>();
    private static final List<Transaction> transactions = new ArrayList<>();
    private static Budget budget;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.add(new User(username, password));
        System.out.println("User registered successfully.");
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = authenticateUser(username, password);
        if (user != null) {
            System.out.println("Login successful.");
            userMenu(scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void userMenu(Scanner scanner) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Set Budget");
            System.out.println("4. View Transactions");
            System.out.println("5. View Budget");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addTransaction(scanner, "income");
                    break;
                case 2:
                    addTransaction(scanner, "expense");
                    break;
                case 3:
                    setBudget(scanner);
                    break;
                case 4:
                    viewTransactions();
                    break;
                case 5:
                    viewBudget();
                    break;
                case 6:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addTransaction(Scanner scanner, String type) {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        LocalDate date = LocalDate.now();

        transactions.add(new Transaction(type, amount, category, date));
        if (type.equals("expense") && budget != null) {
            budget.addExpense(amount);
        }
        System.out.println("Transaction added successfully.");
    }

    private static void setBudget(Scanner scanner) {
        System.out.print("Enter monthly budget: ");
        double monthlyBudget = scanner.nextDouble();
        budget = new Budget(monthlyBudget);
        System.out.println("Budget set successfully.");
    }

    private static void viewTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getDate() + " - " + transaction.getType() + " - " + transaction.getCategory() + " - " + transaction.getAmount());
        }
    }

    private static void viewBudget() {
        if (budget == null) {
            System.out.println("No budget set.");
        } else {
            System.out.println("Monthly Budget: " + budget.getMonthlyBudget());
            System.out.println("Total Spent: " + budget.getTotalSpent());
            System.out.println("Remaining: " + (budget.getMonthlyBudget() - budget.getTotalSpent()));
            System.out.println("Over Budget: " + budget.isOverBudget());
        }
    }
}