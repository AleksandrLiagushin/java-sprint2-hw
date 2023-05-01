package data;

public class MonthExpense {
    public int year;
    public int month;
    public int amount;
    public boolean isExpense;

    public MonthExpense(int year, int month, int amount, boolean isExpense) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }
}
