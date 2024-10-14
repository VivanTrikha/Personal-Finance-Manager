package finance;

public class BudgetController {
    private Budget budget;

    public BudgetController(Budget budget) {
        this.budget = budget;
    }

    public BudgetController() {

    }

    public Budget getBudget() {
        return budget;
    }

    public void addExpense(double amount) {
        if (budget != null) {
            budget.addExpense(amount);
        }
    }
}
