package com.authorization.demo.authservice.persisntance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is the pojo used to store data on the requests made within the application
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", length = 15)
    private int student_id;
    private String firstName;
    private String lastName;
    private String phone;

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    //
//    public Integer calculateYearMark(int exam1, int exam2 ) {
//        exam1= 59; exam2 = 60;
//        yearMark= exam1+exam2/100;
//       return yearMark;
//    }
//
//    public double calculateFees(double outStandingFees) {
//        return fees;
//    }
//
//    // @JsonIgnore
//    @ManyToMany(mappedBy= "studentCourse")
//    private Set<Course> courses;// =new HashSet<>();
//    @Transient
//    private String fullName;


 }

