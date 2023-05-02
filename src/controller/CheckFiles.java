package controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CheckFiles {
    private static final String fileType = "csv";
    private static final String yearInd = "y";
    private static final String monthInd = "m";
    private static ArrayList<String> getFilesNames(String path) {
        ArrayList<String> results = new ArrayList<>();
        File[] files = new File(path).listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isFile() && checkFileNameFormat(file.getName())) {
                results.add(file.getName());
            }
        }
        return results;
    }

    public static ArrayList<String> getMonthReportNames(String path) {
        ArrayList<String> monthReportsNames = new ArrayList<>();
        ArrayList<String> filesNames = getFilesNames(path);
        for (String fileName : filesNames) {
            if (fileName.startsWith(monthInd) && fileName.endsWith(fileType)) {
                monthReportsNames.add(fileName);
            }
        }
        return monthReportsNames;
    }

    public static ArrayList<String> getYearReportNames(String path) {
        ArrayList<String> yearReportsNames = new ArrayList<>();
        ArrayList<String> filesNames = getFilesNames(path);
        for (String fileName : filesNames) {
            if (fileName.startsWith(yearInd) && fileName.endsWith(fileType)) {
                yearReportsNames.add(fileName);
            }
        }
        return yearReportsNames;
    }

    private static boolean checkFileNameFormat (String fileName) {
        String[] splittedName = fileName.split("\\.");
        if (splittedName[2].equals(fileType)) {
            if (splittedName[0].equals(monthInd) && splittedName[1].length() == 6 && splittedName[1].matches("\\d+")) {
                return Integer.parseInt(splittedName[1].substring(4)) >= 1 &&
                        Integer.parseInt(splittedName[1].substring(4)) <= 12;
            } else return splittedName[0].equals(yearInd) && splittedName[1].length() == 4 && splittedName[1].matches("\\d+");
        }
        return false;
    }

    public static ArrayList<String> getAvaliableYears (ArrayList<String> yearReportsList) {
        ArrayList<String> avaliableYears = new ArrayList<>();
        String[] rowYear;
        for (String yearReportName : yearReportsList) {
            rowYear = yearReportName.split("\\.");
            avaliableYears.add(rowYear[1]);
        }
        return avaliableYears;
    }

    public static boolean isPathExist(String path) {
        if (Files.isDirectory(Path.of(path))) {
            return true;
        } else {
            System.out.println("Такой директории не существует, вы направлены в основное меню.\n");
            return false;
        }
    }

    public static String correctPath(String path) {
        if (path.endsWith("/")) {
            return path;
        } else {
            return (path + "/");
        }
    }
}
