package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution {


    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        Path directoryPath = Paths.get(console.readLine());
        if (!Files.isDirectory(directoryPath)) {
            System.out.println(String.format("%s - не папка", directoryPath.toString()));
            return;
        }


        AtomicInteger totalDirs = new AtomicInteger();
        AtomicInteger totalFiles = new AtomicInteger();
        AtomicLong totalSize = new AtomicLong();

        Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                totalDirs.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                totalFiles.incrementAndGet();
                totalSize.addAndGet(attrs.size());
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SUBTREE;
            }
        });

        System.out.println(String.format("Всего папок - %d", totalDirs.get() - 1));
        System.out.println(String.format("Всего файлов - %d", totalFiles.get()));
        System.out.println(String.format("Общий размер - %d", totalSize.get()));
    }
}
