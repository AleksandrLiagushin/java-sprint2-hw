package data;

public class Expense {

    public int year;
    public int month;
    public String itemName;
    public boolean isExpanse;
    public int quantity;
    public int sumOfOne;
    public int amount;

    public Expense(int year, int month, String itemName, boolean isExpanse, int quantity, int sumOfOne) {
        this.year = year;
        this.month = month;
        this.itemName = itemName;
        this.isExpanse = isExpanse;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.amount = quantity * sumOfOne;
    }

    public Expense(String itemName, int amount) {
        this.itemName = itemName;
        this.amount = amount;
    }
}