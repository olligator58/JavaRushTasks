package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student result = null;
        for (Student student : students) {
            if (((Double) student.getAverageGrade()).compareTo(averageGrade) == 0) {
                result = student;
                break;
            }
        }
        return result;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student result = null;
        if (students.size() > 0) {
            result = students.get(0);
            for (int i = 1; i < students.size(); i++) {
                if (students.get(i).getAverageGrade() > result.getAverageGrade()) {
                    result = students.get(i);
                }
            }
        }
        return result;
    }

    public Student getStudentWithMinAverageGrade() {
        Student result = null;
        if (students.size() > 0) {
            result = students.get(0);
            for (int i = 1; i < students.size(); i++) {
                if (students.get(i).getAverageGrade() < result.getAverageGrade()) {
                    result = students.get(i);
                }
            }
        }
        return result;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}