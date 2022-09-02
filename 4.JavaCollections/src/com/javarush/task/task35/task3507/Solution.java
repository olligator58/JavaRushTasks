package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        List<String> fileNames = findCompiledFiles(new File(pathToAnimals).toPath());

        MyClassLoader classLoader = new MyClassLoader();

        for (String fileName : fileNames) {
            try {
                Class<?> clazz = classLoader.loadClass(fileName);
                Class<?>[] interfaces = clazz.getInterfaces();

                boolean hasInterface = false;
                for (Class<?> anInterface : interfaces) {
                    if (anInterface == Animal.class) {
                        hasInterface = true;
                        break;
                    }
                }
                if (!hasInterface) {
                    continue;
                }

                boolean hasConstructor = false;
                Constructor<?>[] constructors = clazz.getConstructors();
                for (Constructor<?> constructor : constructors) {
                    if (constructor.getParameterTypes().length == 0) {
                        hasConstructor = true;
                        break;
                    }
                }
                if (!hasConstructor) {
                    continue;
                }

                Animal animal = (Animal) clazz.newInstance();
                result.add(animal);
            } catch (Exception ignore) {
            }
        }
        return result;
    }

    private static List<String> findCompiledFiles(Path root) {
        List<String> result = new ArrayList<>();
        try {
            Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    result.add(file.toString());
                    return super.visitFile(file, attrs);
                }
            });
        } catch (IOException ignore) {
        }
        return result;
    }

    public static class MyClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) {
            try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(name))) {
                byte[] buffer = new byte[(int) Files.size(Paths.get(name))];
                input.read(buffer);
                Class<?> clazz = defineClass(null, buffer, 0, buffer.length);
                return clazz;
            } catch (IOException ignore) {
            }
            return null;
        }
    }

}
