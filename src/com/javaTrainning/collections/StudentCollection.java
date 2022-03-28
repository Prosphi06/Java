package com.javaTrainning.collections;

import java.util.*;

public class StudentCollection {

   public static void main(String[] args){
        Student st1 = new Student(1,"Prosper Zulu", "B");
        Student st2 = new Student(2,"Bob Mabena", "A");
        Student st3 = new Student(3,"Peter Cozea", "C");

        List<Student> studentsList = new ArrayList<>();

       studentsList.add(st1);
       studentsList.add(st2);
       studentsList.add(st3);

       Set<Student> studentsSet = new HashSet<>();
       studentsSet.add(st1);
       studentsSet.add(st2);
       studentsSet.add(st3);

       for (Student ss: studentsSet) {
           System.out.println(ss);
       }
       for (Student s: studentsList) {
           System.out.println(s);
       }

       studentsList.remove(1);
       for (Student s: studentsList) {
           System.out.println(s);
       }

       Student student = (Student) studentsList.get(0);
       System.out.println("Get Student Grade: " + student.getGrade());

       Set<Student> sortStudents = new TreeSet<>(Comparator.comparing(Student::getGrade));
       sortStudents.add(st1);
       sortStudents.add(st2);
       sortStudents.add(st3);

      // System.out.println("Student by Grade: ");
       for (Student s: sortStudents) {
           System.out.println("Student by Grade: "+ s);
       }

//       int  gradePosition = -1;
//       gradePosition = studentsList.indexOf("C");
//       if(gradePosition == -1) {
//           System.out.println("Grade not found in the list");
//       }else {
//           System.out.println("Grade C is found on index: " + gardePosition);
//       }

    }

}
