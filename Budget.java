package finance;
public class Budget {
    private final double monthlyBudget;
    private double totalSpent;

    public Budget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
        this.totalSpent = 0;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void addExpense(double amount) {
        this.totalSpent += amount;
    }

    public boolean isOverBudget() {
        return totalSpent > monthlyBudget;
    }
}
