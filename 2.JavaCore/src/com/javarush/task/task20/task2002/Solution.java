package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        try {
            File yourFile = File.createTempFile("JavaRush", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user1 = new User();
            user1.setFirstName("Иван");
            user1.setLastName("Будько");
            user1.setBirthDate(new Date(56, 04, 12));
            user1.setMale(true);
            user1.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user1);
            User user2 = new User();
            user2.setFirstName("Клавдия");
            user2.setLastName("Прихожкина");
            user2.setBirthDate(new Date(102, 00, 31));
            user2.setMale(false);
            user2.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user2);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            System.out.println(javaRush.equals(loadedObject));


            /*for (User user : loadedObject.users) {
                System.out.println(user.getFirstName());
                System.out.println(user.getLastName());
                System.out.println(user.getBirthDate());
                System.out.println(user.isMale());
                System.out.println(user.getCountry().getDisplayName());
            }*/

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
                if (!users.isEmpty()) {
                    writer.write("full" + "\n");
                    for (User user : users) {
                        writer.write(user.getFirstName() + "\n");
                        writer.write(user.getLastName() + "\n");
                        writer.write(user.getBirthDate().getTime() + "\n");
                        writer.write(user.isMale() + "\n");
                        writer.write(user.getCountry().getDisplayName() + "\n");
                    }
                } else {
                    writer.write("empty" + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void load(InputStream inputStream) throws Exception {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                if (reader.readLine().equals("full")) {
                    while (reader.ready()) {
                        User user = new User();
                        user.setFirstName(reader.readLine());
                        user.setLastName(reader.readLine());
                        user.setBirthDate(new Date(Long.parseLong(reader.readLine())));
                        user.setMale(Boolean.parseBoolean(reader.readLine()));
                        User.Country country = null;
                        switch (reader.readLine()) {
                            case "Ukraine":
                                country = User.Country.UKRAINE;
                                break;
                            case "Russia":
                                country = User.Country.RUSSIA;
                                break;
                            case "Other":
                                country = User.Country.OTHER;
                                break;
                            default:
                        }
                        user.setCountry(country);
                        users.add(user);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
