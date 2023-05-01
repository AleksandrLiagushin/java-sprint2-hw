package data;

import java.util.ArrayList;

public class YearReports {
    public ArrayList<MonthExpense> monthExpenses = new ArrayList<>();

    public void YearReportsLoad(String path) {
        String[] rowLine;
        String[] rowLines = controller.FileLoader.readFileContentsOrNull(path).split("\r?\n");
        String[] rowPathLine = path.split("/");
        String[] rowFileName = rowPathLine[rowPathLine.length - 1].split("\\.");
        int year = Integer.parseInt(rowFileName[1]);
        for (int i = 1; i < rowLines.length; i++) {
            rowLine = rowLines[i].split(",");
            int month = Integer.parseInt(rowLine[0]);
            int amount = Integer.parseInt(rowLine[1]);
            boolean isExpanse = Boolean.parseBoolean(rowLine[2]);
            var expense = new MonthExpense(year, month, amount, isExpanse);
            monthExpenses.add(expense);
        }
        System.out.println("Годовой отчет " + rowPathLine[rowPathLine.length - 1] + " успешно загружен.");
    }
}
