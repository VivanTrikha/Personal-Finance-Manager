public class Budget {
    private double monthlyBudget;
    private double totalSpent;

    public Budget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
        this.totalSpent = 0;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void addExpense(double amount) {
        this.totalSpent += amount;
    }

    public void resetTotalSpent() {
        this.totalSpent = 0;
    }

    public boolean isOverBudget() {
        return totalSpent > monthlyBudget;
    }
}
