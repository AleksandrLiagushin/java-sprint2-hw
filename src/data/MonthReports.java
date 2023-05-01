package data;

import java.util.ArrayList;

public class MonthReports {

    public ArrayList<Expense> expenses = new ArrayList<>();
    public void MonthReportsLoad(String path) {
        String[] rowLine;
        String[] rowLines = controller.FileLoader.readFileContentsOrNull(path).split("\r?\n");
        String[] rowPathLine = path.split("/");
        String[] rowFileName = rowPathLine[rowPathLine.length - 1].split("\\.");
        int year = Integer.parseInt(rowFileName[1].substring(0, 4));
        int month = Integer.parseInt(rowFileName[1].substring(4));
        for (int i = 1; i < rowLines.length; i++) {
            rowLine = rowLines[i].split(",");
            String itemName = rowLine[0];
            boolean isExpanse = Boolean.parseBoolean(rowLine[1]);
            int quantity = Integer.parseInt(rowLine[2]);
            int sumOfOne = Integer.parseInt(rowLine[3]);
            var expense = new Expense(year, month, itemName, isExpanse, quantity, sumOfOne);
            expenses.add(expense);
        }

        System.out.println("Месячный отчет " + rowPathLine[rowPathLine.length - 1] + " успешно загружен.");
    }

}
