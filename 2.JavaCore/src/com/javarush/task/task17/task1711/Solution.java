package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        int numGroups;
        String name;
        String sex;
        Date bd;
        Person person;
        int id;
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        if (args == null || args.length < 2) {
            throw new RuntimeException("Количество входных параметров должно быть не меньше 2");
        }

        switch (args[0]) {
            case "-c":
                numGroups = (args.length - 1) / 3;
                for (int i = 0; i < numGroups; i++) {
                    name = args[i * 3 + 1];
                    sex = args[i * 3 + 2];
                    bd = inputFormat.parse(args[i * 3 + 3]);
                    if (sex.equalsIgnoreCase("м")) {
                        person = Person.createMale(name, bd);
                    } else {
                        person = Person.createFemale(name, bd);
                    }
                    synchronized (allPeople) {
                        allPeople.add(person);
                        System.out.println(allPeople.indexOf(person));
                    }
                }
                break;
            case "-u":
                numGroups = (args.length - 1) / 4;
                for (int i = 0; i < numGroups; i++) {
                    id = Integer.parseInt(args[i * 4 + 1]);
                    name = args[i * 4 + 2];
                    sex = args[i * 4 + 3];
                    Sex personSex = (sex.equalsIgnoreCase("м")) ? Sex.MALE : Sex.FEMALE;
                    bd = inputFormat.parse(args[i * 4 + 4]);
                    synchronized (allPeople) {
                        allPeople.get(id).setName(name);
                        allPeople.get(id).setSex(personSex);
                        allPeople.get(id).setBirthDate(bd);
                    }
                }
                break;
            case "-d":
                for (int i = 1; i < args.length; i++) {
                    id = Integer.parseInt(args[i]);
                    synchronized (allPeople) {
                        allPeople.get(id).setName(null);
                        allPeople.get(id).setSex(null);
                        allPeople.get(id).setBirthDate(null);
                    }
                }
                break;
            case "-i":
                for (int i = 1; i < args.length; i++) {
                    id = Integer.parseInt(args[i]);
                    synchronized (allPeople) {
                        person = allPeople.get(id);
                    }
                    name = person.getName();
                    sex = (person.getSex() == Sex.MALE) ? "м" : "ж";
                    bd = person.getBirthDate();
                    System.out.println(name + " " + sex + " " + outputFormat.format(bd));
                }
                break;
        }

        /*for (Person p : allPeople) {
            System.out.println(p.getName() + " " + p.getSex() + " " + p.getBirthDate());
        }*/
    }
}
