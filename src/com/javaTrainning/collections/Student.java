package com.javaTrainning.collections;

public class Student {
    private int id;
    private String fullName;
    private String dateOfBirth;
    private String grade;
    private String course;

    public Student(){

    }
    public Student(int id, String fullName, String grade){
    this.id = id;
    this.fullName = fullName;
    this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student { " +
                "id='" + id + '\'' +
                "fullName='" + fullName + '\'' +
                "grade='" + grade + " " +
        '}';
    }
}
