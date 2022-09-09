package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("", "");
            path.toFile().deleteOnExit();

            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException ignored) {
        }
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException ignored) {
        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try (OutputStream outputStream = Files.newOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(entry);
        } catch (IOException ignored) {
        }
    }

    public Entry getEntry() {
        Entry result = null;
        if (getFileSize() > 0) {
            try (InputStream inputStream = Files.newInputStream(path);
                 ObjectInputStream ois = new ObjectInputStream(inputStream);) {
                result = (Entry) ois.readObject();
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }
}
