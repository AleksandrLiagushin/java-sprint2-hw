package businesLogic;

public class DiscrepanciesReports {
    public int year;
    public int month;
    public boolean isExpanse;
    public int discrepancySum;

    public DiscrepanciesReports(int year, int month, boolean isExpanse, int discrepancySum) {
        this.year = year;
        this.month = month;
        this.isExpanse = isExpanse;
        this.discrepancySum = discrepancySum;
    }
}
