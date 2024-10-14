package finance;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard extends Application {
    private final TransactionController transactionController = new TransactionController();
    private final BudgetController budgetController = new BudgetController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Personal Finance Manager Dashboard");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome to your Dashboard");
        vbox.getChildren().add(welcomeLabel);

        Button addIncomeButton = new Button("Add Income");
        Button addExpenseButton = new Button("Add Expense");
        Button setBudgetButton = new Button("Set Budget");
        Button viewTransactionsButton = new Button("View Transactions");
        Button viewBudgetButton = new Button("View Budget");
        Button logoutButton = new Button("Logout");

        vbox.getChildren().addAll(addIncomeButton, addExpenseButton, setBudgetButton, viewTransactionsButton, viewBudgetButton, logoutButton);

        // Add functionality to buttons
        addIncomeButton.setOnAction(e -> handleAddIncome(primaryStage));
        addExpenseButton.setOnAction(e -> handleAddExpense(primaryStage));
        setBudgetButton.setOnAction(e -> handleSetBudget(primaryStage));
        viewTransactionsButton.setOnAction(e -> handleViewTransactions(primaryStage));
        viewBudgetButton.setOnAction(e -> handleViewBudget(primaryStage));
        logoutButton.setOnAction(e -> primaryStage.setScene(createLoginScene()));

        Scene scene = new Scene(vbox, 400, 300);
        scene.getStylesheets().add(getClass().getResource("finance/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene createLoginScene() {
        // Implementation for the login scene (similar to the one in Main.java)
        return null;
    }

    private void handleAddIncome(Stage primaryStage) {
        primaryStage.setScene(createAddTransactionScene("income"));
    }

    private void handleAddExpense(Stage primaryStage) {
        primaryStage.setScene(createAddTransactionScene("expense"));
    }

    private void handleSetBudget(Stage primaryStage) {
        primaryStage.setScene(createSetBudgetScene());
    }

    private void handleViewTransactions(Stage primaryStage) {
        primaryStage.setScene(createViewTransactionsScene());
    }

    private void handleViewBudget(Stage primaryStage) {
        primaryStage.setScene(createViewBudgetScene());
    }

    private Scene createAddTransactionScene(String type) {
        // Implementation similar to the one in Main.java
        return null;
    }

    private Scene createSetBudgetScene() {
        // Implementation similar to the one in Main.java
        return null;
    }

    private Scene createViewTransactionsScene() {
        // Implementation similar to the one in Main.java
        return null;
    }

    private Scene createViewBudgetScene() {
        // Implementation similar to the one in Main.java
        return null;
    }
}
