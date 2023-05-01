package businesLogic;

import data.MonthExpense;
import data.MonthReports;
import data.YearReports;

import java.util.ArrayList;

public class CompareReports {

    public static ArrayList<DiscrepanciesReports> compareReports(MonthReports monthReports, YearReports yearReports, int year) {
        ArrayList<DiscrepanciesReports> allDiscrepByMonth = new ArrayList<>();
        for (MonthExpense monthExpense : yearReports.monthExpenses) {
            if (monthExpense.isExpense) {
                int fullMonthExpense = ReportStatistics.getFullMonthExpense(monthReports, year, monthExpense.month);
                if (monthExpense.amount != fullMonthExpense) {
                    var difference = monthExpense.amount - fullMonthExpense;
                    var monthDescrep = new DiscrepanciesReports(monthExpense.year, monthExpense.month, true, difference);
                    allDiscrepByMonth.add(monthDescrep);
                }
            } else {
                int fullMonthRevenue = ReportStatistics.getFullMonthRevenue(monthReports, year, monthExpense.month);
                if (monthExpense.amount != fullMonthRevenue) {
                    var difference = monthExpense.amount - fullMonthRevenue;
                    var monthDescrep = new DiscrepanciesReports(monthExpense.year, monthExpense.month, true, difference);
                    allDiscrepByMonth.add(monthDescrep);
                }
            }
        }
        return allDiscrepByMonth;
    }
}
