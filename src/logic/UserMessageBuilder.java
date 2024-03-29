package logic;

import businesLogic.DiscrepanciesReports;
import businesLogic.ReportStatistics;
import controller.CheckFiles;
import data.MonthReports;
import data.YearReports;

import java.util.ArrayList;
import java.util.Scanner;

public class UserMessageBuilder {

    private static void printAvaliableYears(ArrayList<String> avaliableYears) {
        for (String avaliableYear : avaliableYears) {
            System.out.println(parseFileName(avaliableYear));
        }

    }




    public static int getYear(Scanner scanner, ArrayList<String> yearReportsList) {
        if (yearReportsList.size() > 1) {
            System.out.println("Доступные годы для выбора:");
            printAvaliableYears(CheckFiles.getAvaliableYears(yearReportsList));
            System.out.println("Введите год:");
            return scanner.nextInt();
        }
        return Integer.parseInt(parseFileName(yearReportsList.get(0)));
    }

    public static void printMonthReport(MonthReports monthReports, int year, int month) {
        var monthMaxRevenue = ReportStatistics.getMonthMaxRevenue(monthReports, year, month);
        var monthMaxExpanse = ReportStatistics.getMonthMaxExpanse(monthReports, year, month);
        System.out.println("Ежемесячный отчет за " + monthNamer(month) + " " + year);
        System.out.println("Самый прибыльный товар: " + monthMaxRevenue.itemName +
                " на сумму: " + monthMaxRevenue.amount);
        System.out.println("Самая большая трата: " + monthMaxExpanse.itemName +
                " на сумму: " + monthMaxExpanse.amount);
        System.out.println("Выручка за месяц: " + ReportStatistics.getFullMonthRevenue(monthReports, year, month));
        System.out.println("Всего затрат: " + ReportStatistics.getFullMonthExpense(monthReports, year, month) + "\n");
    }

    public static void printYearReport(YearReports yearReports, int year) {
        System.out.println("Отчет за " + year);
        var profitByMonth = ReportStatistics.getMonthProfitForYear(yearReports, year);
        var avMonth = profitByMonth.keySet();
        for (int month : avMonth) {
            String profitToPrint = String.format("Прибыль в %s составила: %d", monthNamer(month), profitByMonth.get(month));
            System.out.println(profitToPrint);
        }
        System.out.println("Средняя выручка составила: " +
                ReportStatistics.getYearTotalRevenue(yearReports, year) / avMonth.size());
        System.out.println("Средний убыток составил: " +
                ReportStatistics.getYearTotalExpanse(yearReports, year) / avMonth.size() + "\n");
    }

    public static void printComparedReports(ArrayList<DiscrepanciesReports> discrepanciesReports, int year) {
        System.out.println("Сверка отчетов за " + year);
        if (discrepanciesReports.size() > 0) {
            for (DiscrepanciesReports report : discrepanciesReports) {
                if (report.isExpanse) {
                    System.out.println(monthNamer(report.month) + "обнаружено расхождение в расходах в размере " +
                            report.discrepancySum);
                } else {
                    System.out.println(monthNamer(report.month) + "обнаружено расхождение в выручке в размере " +
                            report.discrepancySum);
                }
            }
            System.out.println();
        } else {
            System.out.println("Расхождений в отчетах не обнаружено.\n");
        }
    }

    public static String parseFileName(String fileName) {
        String[] splittedName = fileName.split("\\.");
        return splittedName[1];
    }

    private static String monthNamer(int month) {
        if (month == 1) {
            return ("Январь");
        } else if (month == 2) {
            return ("Февраль");
        } else if (month == 3) {
            return ("Март");
        } else if (month == 4) {
            return ("Апрель");
        } else if (month == 5) {
            return ("Май");
        } else if (month == 6) {
            return ("Июнь");
        } else if (month == 7) {
            return ("Июль");
        } else if (month == 8) {
            return ("Август");
        } else if (month == 9) {
            return ("Сентябрь");
        } else if (month == 10) {
            return ("Октябрь");
        } else if (month == 11) {
            return ("Ноябрь");
        } else if (month == 12) {
            return ("Декабрь");
        }
        return "";
    }
}
