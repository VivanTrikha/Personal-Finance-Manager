package finance;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static final List<User> users = new ArrayList<>();
    private static final List<Transaction> transactions = new ArrayList<>();
    private static Budget budget;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Personal Finance Manager");
        primaryStage.setScene(createLoginScene());
        primaryStage.show();
    }

    private Scene createLoginScene() {
        GridPane grid = createGridPane();

        Label userLabel = new Label("Username:");
        grid.add(userLabel, 0, 0);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 0);

        Label pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 1);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 1);

        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 2);

        Button registerButton = new Button("Register");
        grid.add(registerButton, 1, 3);

        loginButton.setOnAction(e -> handleLogin(userTextField.getText(), pwBox.getText()));
        registerButton.setOnAction(e -> handleRegister(userTextField.getText(), pwBox.getText()));

        Scene scene = new Scene(grid, 400, 300);
        applyStylesheet(scene);
        return scene;
    }

    private Scene createDashboardScene() {
        VBox vbox = createVBox();

        Button addIncomeButton = new Button("Add Income");
        Button addExpenseButton = new Button("Add Expense");
        Button setBudgetButton = new Button("Set Budget");
        Button viewTransactionsButton = new Button("View Transactions");
        Button viewBudgetButton = new Button("View Budget");
        Button logoutButton = new Button("Logout");

        addIncomeButton.setOnAction(e -> primaryStage.setScene(createTransactionScene("income")));
        addExpenseButton.setOnAction(e -> primaryStage.setScene(createTransactionScene("expense")));
        setBudgetButton.setOnAction(e -> primaryStage.setScene(createSetBudgetScene()));
        viewTransactionsButton.setOnAction(e -> primaryStage.setScene(createViewTransactionsScene()));
        viewBudgetButton.setOnAction(e -> primaryStage.setScene(createViewBudgetScene()));
        logoutButton.setOnAction(e -> primaryStage.setScene(createLoginScene()));

        vbox.getChildren().addAll(addIncomeButton, addExpenseButton, setBudgetButton, viewTransactionsButton, viewBudgetButton, logoutButton);

        Scene scene = new Scene(vbox, 400, 300);
        applyStylesheet(scene);
        return scene;
    }

    private Scene createTransactionScene(String type) {
        GridPane grid = createGridPane();

        Label amountLabel = new Label("Amount:");
        grid.add(amountLabel, 0, 0);

        TextField amountField = new TextField();
        grid.add(amountField, 1, 0);

        Label categoryLabel = new Label("Category:");
        grid.add(categoryLabel, 0, 1);

        TextField categoryField = new TextField();
        grid.add(categoryField, 1, 1);

        Button addButton = new Button("Add " + type);
        grid.add(addButton, 1, 2);

        addButton.setOnAction(e -> handleAddTransaction(type, amountField.getText(), categoryField.getText()));

        Scene scene = new Scene(grid, 400, 300);
        applyStylesheet(scene);
        return scene;
    }

    private Scene createSetBudgetScene() {
        GridPane grid = createGridPane();

        Label budgetLabel = new Label("Monthly Budget:");
        grid.add(budgetLabel, 0, 0);

        TextField budgetField = new TextField();
        grid.add(budgetField, 1, 0);

        Button setButton = new Button("Set Budget");
        grid.add(setButton, 1, 1);

        setButton.setOnAction(e -> handleSetBudget(budgetField.getText()));

        Scene scene = new Scene(grid, 400, 300);
        applyStylesheet(scene);
        return scene;
    }

    private Scene createViewTransactionsScene() {
        VBox vbox = createVBox();

        for (Transaction transaction : transactions) {
            Label transactionLabel = new Label(transaction.getDate() + " - " + transaction.getType() + " - " + transaction.getCategory() + " - " + transaction.getAmount());
            vbox.getChildren().add(transactionLabel);
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(createDashboardScene()));
        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 400, 300);
        applyStylesheet(scene);
        return scene;
    }

    private Scene createViewBudgetScene() {
        VBox vbox = createVBox();

        if (budget == null) {
            vbox.getChildren().add(new Label("No budget set."));
        } else {
            vbox.getChildren().add(new Label("Monthly Budget: " + budget.getMonthlyBudget()));
            vbox.getChildren().add(new Label("Total Spent: " + budget.getTotalSpent()));
            vbox.getChildren().add(new Label("Remaining: " + (budget.getMonthlyBudget() - budget.getTotalSpent())));
            vbox.getChildren().add(new Label("Over Budget: " + budget.isOverBudget()));
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(createDashboardScene()));
        vbox.getChildren().add(backButton);

        Scene scene = new Scene(vbox, 400, 300);
        applyStylesheet(scene);
        return scene;
    }

    private void handleLogin(String username, String password) {
        User user = authenticateUser(username, password);
        if (user != null) {
            primaryStage.setScene(createDashboardScene());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.");
            alert.showAndWait();
        }
    }

    private void handleRegister(String username, String password) {
        users.add(new User(username, password));
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "User registered successfully.");
        alert.showAndWait();
    }

    private User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void handleAddTransaction(String type, String amountStr, String category) {
        try {
            double amount = Double.parseDouble(amountStr);
            LocalDate date = LocalDate.now();

            transactions.add(new Transaction(type, amount, category, date));
            if (type.equals("expense") && budget != null) {
                budget.addExpense(amount);
            }
            primaryStage.setScene(createDashboardScene());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid amount.");
            alert.showAndWait();
        }
    }

    private void handleSetBudget(String amountStr) {
        try {
            double amount = Double.parseDouble(amountStr);
            budget = new Budget(amount);
            primaryStage.setScene(createDashboardScene());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid amount.");
            alert.showAndWait();
        }
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

    private VBox createVBox() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private void applyStylesheet(Scene scene) {
        String stylesheet = getClass().getResource("finance/style.css") != null ? getClass().getResource("finance/style.css").toExternalForm() : null;
        if (stylesheet != null) {
            scene.getStylesheets().add(stylesheet);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
