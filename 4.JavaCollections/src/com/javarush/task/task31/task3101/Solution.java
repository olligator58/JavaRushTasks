package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/* 
Проход по дереву файлов
*/

public class Solution {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("Должно быть передано 2 параметра");
        }

        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File resultFile = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");

        if (FileUtils.isExist(resultFile)) {
            FileUtils.deleteFile(resultFile);
        }

        FileUtils.renameFile(resultFileAbsolutePath, resultFile);

        List<File> files = new ArrayList<>();
        getAllFilesInDirectory(path, files);

        try (FileOutputStream output = new FileOutputStream(resultFile)) {
            for (File file : files) {
                if (file.length() <= 50) {
                    output.write(Files.readAllBytes(file.toPath()));
                    output.write("\n".getBytes());
                }
            }
        } catch (IOException ignore) {
        }
    }

    private static void getAllFilesInDirectory(File file, List<File> result) {
        if (file.isFile()) {
            result.add(file);
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File currentFile : files) {
                getAllFilesInDirectory(currentFile, result);
            }
        }
    }
}
