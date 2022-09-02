package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        File filePackage = new File(packageName);

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> findClass(String name) throws ClassNotFoundException {
                File absoluteFileName = filePackage.toPath().resolve(Paths.get(name)).toFile();

                try (FileInputStream input = new FileInputStream(absoluteFileName);) {
                    byte[] buffer = new byte[(int) Files.size(absoluteFileName.toPath())];
                    input.read(buffer);
                    return defineClass(null, buffer, 0, buffer.length);
                } catch (IOException ignored) {
                }
                return null;
            }
        };

        for (String fileName : filePackage.list()) {
            if (fileName.endsWith(".class")) {
                Class<?> clazz = classLoader.loadClass(fileName);
                if (HiddenClass.class.isAssignableFrom(clazz)) {
                    hiddenClasses.add(clazz);
                }
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class hiddenClass : hiddenClasses) {
            if (hiddenClass.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    Constructor constructor = hiddenClass.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    HiddenClass newInstance = (HiddenClass) constructor.newInstance();
                    return newInstance;
                } catch (Exception ignored) {
                }
            }
        }
        return null;
    }
}

