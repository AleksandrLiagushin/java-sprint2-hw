package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileLoader {

    public static String readFileContentsOrNull (String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчетом. Возможно файл находится в другой директории.");
            return null;
        }
    }

    public String getPath(Scanner scanner) {
        System.out.println("Укажите путь к папке с отчетами");
        String path = scanner.nextLine();
        return CheckFiles.correctPath(path);
    }
}
