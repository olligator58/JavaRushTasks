package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new RuntimeException("В программу должно быть передано 2 аргумента");
        }

        String fileName = args[0];
        String zipFileName = args[1];

        Map<String, ByteArrayOutputStream> zipContent = new HashMap<>();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry zipEntry;
            byte[] buffer = new byte[8 * 1024];
            int count;
            while ((zipEntry = zis.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((count = zis.read(buffer)) > 0) {
                    baos.write(buffer, 0, count);
                }
                zipContent.put(zipEntry.getName(), baos);
                zis.closeEntry();
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(Files.readAllBytes(Paths.get(fileName)));
        zipContent.put("new/" + Paths.get(fileName).getFileName().toString(), baos);

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            for (Map.Entry<String, ByteArrayOutputStream> pair : zipContent.entrySet()) {
                ZipEntry zipEntry = new ZipEntry(pair.getKey());
                zos.putNextEntry(zipEntry);
                zos.write(pair.getValue().toByteArray());
                zos.closeEntry();
            }
        }
    }
}
