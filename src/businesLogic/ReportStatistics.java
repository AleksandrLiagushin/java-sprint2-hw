package businesLogic;

import data.Expense;
import data.MonthExpense;
import data.MonthReports;
import data.YearReports;
import java.util.HashMap;

public class ReportStatistics {

    public static Expense getMonthMaxExpanse(MonthReports monthReports, int year, int month) {
        int maxExpanse = 0;
        String maxExpanseName = null;
        for (Expense expense : monthReports.expenses) {
            if (expense.year == year && expense.month == month && expense.isExpanse) {
                if (expense.amount > maxExpanse) {
                    maxExpanse = expense.amount;
                    maxExpanseName = expense.itemName;
                }
            }
        }
        var monthMaxExpanse = new Expense(maxExpanseName, maxExpanse);
        monthMaxExpanse.itemName = maxExpanseName;
        monthMaxExpanse.amount = maxExpanse;
        return monthMaxExpanse;
    }

    public static Expense getMonthMaxRevenue(MonthReports monthReports, int year, int month) {
        int maxRevenue = 0;
        String maxRevenueName = null;
        for (Expense expense : monthReports.expenses) {
            if (expense.year == year && expense.month == month && !expense.isExpanse) {
                if (expense.amount > maxRevenue) {
                    maxRevenue = expense.amount;
                    maxRevenueName = expense.itemName;
                }
            }
        }
        var monthMaxRevenue = new Expense(maxRevenueName, maxRevenue);
        monthMaxRevenue.itemName = maxRevenueName;
        monthMaxRevenue.amount = maxRevenue;
        return monthMaxRevenue;
    }

   public static int getYearTotalExpanse(YearReports yearReports, int year) {
        int totalExpanse = 0;
        for (MonthExpense expense : yearReports.monthExpenses) {
            if (expense.year == year && expense.isExpense) {
                totalExpanse += expense.amount;
            }
        }
        return totalExpanse;
    }

    public static int getYearTotalRevenue(YearReports yearReports, int year) {
        int totalRevenue = 0;
        for (MonthExpense expense : yearReports.monthExpenses) {
            if (expense.year == year && !expense.isExpense) {
                totalRevenue += expense.amount;
            }
        }
        return totalRevenue;
    }

    public static int getFullMonthExpense(MonthReports monthReports, int year, int month) {
        int fullMonthExpanse = 0;
        for (Expense expense : monthReports.expenses) {
            if (expense.year == year && expense.month == month && expense.isExpanse) {
                fullMonthExpanse += expense.amount;
            }
        }
        return fullMonthExpanse;
    }

    public static int getFullMonthRevenue(MonthReports monthReports, int year, int month) {
        int fullMonthRevenue = 0;
        for (Expense expense : monthReports.expenses) {
            if (expense.year == year && expense.month == month && !expense.isExpanse) {
                fullMonthRevenue += expense.amount;
            }
        }
        return fullMonthRevenue;
    }

    public static HashMap<Integer, Integer> getMonthProfitForYear (YearReports yearReports, int year) {
        var profitByMonth = new HashMap<Integer, Integer>();
        int revenue = 0;
        int expense = 0;
        int profit;
        for (MonthExpense monthExpense : yearReports.monthExpenses) {
            if (monthExpense.year == year) {
                if (monthExpense.isExpense) {
                    expense = monthExpense.amount;
                } else {
                    revenue = monthExpense.amount;
                }
                profit = revenue - expense;
                profitByMonth.put(monthExpense.month, profit);
            }
        }
        return profitByMonth;
    }
}
