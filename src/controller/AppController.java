package controller;

import businesLogic.CompareReports;
import data.MonthReports;
import data.YearReports;
import logic.UserMessageBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class AppController {
    private static final int LOAD_DATA = 1;
    private static final int PRINT_MON_DATA = 2;
    private static final int PRINT_YEAR_DATA = 3;
    private static final int CHECK_REPORTS = 4;
    private static final int QUIT_APP = 0;

    public void startApp() {
        var userInput = new Scanner(System.in);
        var monthReports = new MonthReports();
        var yearReports = new YearReports();
        ArrayList<String> yearReportsList = null;
        ArrayList<String> monthReportsList = null;
        int year;
        int month;
        String path;
        while (true) {
            printMainMenu();
            int command = userInput.nextInt();
            userInput.nextLine();
            if (command == LOAD_DATA) {
                path = new FileLoader().getPath(userInput);
                if (CheckFiles.isPathExist(path)) {
                    monthReportsList = CheckFiles.getMonthReportNames(path);
                    if (monthReportsList.size() != 0) {
                        for (String monthReport : monthReportsList) {
                            monthReports.MonthReportsLoad(path + monthReport);
                        }
                    } else {
                        System.out.println("Файлы ежемесячных отчетов не найдены в указанной директории.\n");
                        continue;
                    }
                    yearReportsList = CheckFiles.getYearReportNames(path);
                    if (yearReportsList.size() != 0) {
                        for (String yearReport : yearReportsList) {
                            yearReports.YearReportsLoad(path + yearReport);
                        }
                    } else {
                        System.out.println("Файлы годовых отчетов не найдены в указанной директории.\n");
                    }

                }
            } else if (command == PRINT_MON_DATA) {
                printMonthReportsMenu();
                if (monthReportsList != null && yearReportsList != null) {
                    year = UserMessageBuilder.getYear(userInput, yearReportsList);
                    month = UserMessageBuilder.getMonth(userInput, monthReportsList, year);
                    UserMessageBuilder.printMonthReport(monthReports, year, month);
                } else {
                    System.out.println("Месячные отчеты не были подгружены.\n");
                }
            } else if (command == PRINT_YEAR_DATA) {
                if (yearReportsList != null) {
                    year = UserMessageBuilder.getYear(userInput, yearReportsList);
                    UserMessageBuilder.printYearReport(yearReports, monthReportsList, year);
                } else {
                    System.out.println("Годовые отчеты не были подгружены.\n");
                }
            } else if (command == CHECK_REPORTS) {
                if (monthReportsList != null && yearReportsList != null) {
                    year = UserMessageBuilder.getYear(userInput, yearReportsList);
                    var discrReports = CompareReports.compareReports(monthReports, yearReports, year);
                    UserMessageBuilder.printComparedReports(discrReports, year);
                } else {
                    System.out.println("Часть отчетов не была подгужена");
                }
            } else if (command == QUIT_APP) {
                System.out.println("Работа приложения успешно заввершена.");
                break;
            } else {
                System.out.println("Такой команды нет. Повторите ввод:");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("Бухгалтерия. Сверка отчетности.");
        System.out.println("1 - Загрузить данные из файлов.");
        System.out.println("2 - Распечатать месячный отчет.");
        System.out.println("3 - Распечатать годовой отчет.");
        System.out.println("4 - Сверить отчеты.");
        System.out.println("0 - Выход.");
    }

    private static void printMonthReportsMenu() {
        System.out.println("1 - распечатать все отчеты за выбранный год");
        System.out.println("2 - распечатать подробный отчет за месяц");
    }
}
