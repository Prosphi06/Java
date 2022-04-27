package com.postgres.learning.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(initialValue = 1, name = "emp_seq", sequenceName = "employee_sequence")
    private Integer  id;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "id"), name = "id")
    private Department department;
}
