package com.javarush.task.pro.task15.task1519;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Поверхностное копирование
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path sourceDirectory = Paths.get(scanner.nextLine());
        Path targetDirectory = Paths.get(scanner.nextLine());
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(sourceDirectory)) {
            for (Path path: paths) {
                if (Files.isRegularFile(path)) {
                    Path targetFile = targetDirectory.resolve(path.getFileName());
                    Files.copy(path, targetFile);
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}

