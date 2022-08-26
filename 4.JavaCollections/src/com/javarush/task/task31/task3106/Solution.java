package com.javarush.task.task31.task3106;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new RuntimeException("В программу должны быть переданы параметры");
        }

        String resultFileName = args[0];
        List<String> fileNameParts = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            fileNameParts.add(args[i]);
        }

        Collections.sort(fileNameParts);
        List<FileInputStream> fileInputStreams = new ArrayList<>();
        for (String fileNamePart : fileNameParts) {
            fileInputStreams.add(new FileInputStream(fileNamePart));
        }

        try (ZipInputStream zis = new ZipInputStream(new SequenceInputStream(Collections.enumeration(fileInputStreams)));
             FileOutputStream fos = new FileOutputStream(resultFileName)) {

            zis.getNextEntry();
            byte[] buffer = new byte[1024 * 1024];
            int count;
            while ((count = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            zis.closeEntry();
        }
    }
}
