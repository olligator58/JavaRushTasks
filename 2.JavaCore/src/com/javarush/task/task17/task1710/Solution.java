package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        Person person = null;
        String name;
        String sex;
        int id;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date;
        Date bd;

        switch (args[0]) {
            case "-c":
                name = args[1];
                sex = args[2];
                date = LocalDate.parse(args[3], dtf);
                bd = new Date(date.getYear() - 1900, date.getMonthValue() - 1, date.getDayOfMonth());
                if (sex.equalsIgnoreCase("м")) {
                    person = Person.createMale(name, bd);
                } else if (sex.equalsIgnoreCase("ж")) {
                    person = Person.createFemale(name, bd);
                }
                allPeople.add(person);
                System.out.println(allPeople.indexOf(person));
                break;
            case "-r":
                id = Integer.parseInt(args[1]);
                person = allPeople.get(id);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                sex = (person.getSex() == Sex.MALE) ? "м" : "ж";
                System.out.println(person.getName() + " " + sex + " " + sdf.format(person.getBirthDate()));
                break;
            case "-u":
                id = Integer.parseInt(args[1]);
                name = args[2];
                sex = args[3];
                Sex personSex = (sex.equalsIgnoreCase("м")) ? Sex.MALE : Sex.FEMALE;
                date = LocalDate.parse(args[4], dtf);
                bd = new Date(date.getYear() - 1900, date.getMonthValue() - 1, date.getDayOfMonth());
                allPeople.get(id).setName(name);
                allPeople.get(id).setSex(personSex);
                allPeople.get(id).setBirthDate(bd);
                break;
            case "-d":
                id = Integer.parseInt(args[1]);
                allPeople.get(id).setName(null);
                allPeople.get(id).setSex(null);
                allPeople.get(id).setBirthDate(null);
                break;
        }

        /*for (Person aperson : allPeople) {
            System.out.println(aperson.getName() + " " + aperson.getSex() + " " + aperson.getBirthDate());
        }*/
    }
}
