package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<>();
        Files.walkFileTree(Paths.get(root), EnumSet.of(FileVisitOption.FOLLOW_LINKS), 20,
                new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        result.add(file.toString());
                        return super.visitFile(file, attrs);
                    }
                });
        return result;
    }

    public static void main(String[] args) {

    }
}
