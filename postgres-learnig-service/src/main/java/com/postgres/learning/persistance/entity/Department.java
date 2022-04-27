package com.postgres.learning.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
    @SequenceGenerator(initialValue = 1, name = "dept_seq", sequenceName = "dept_sequence")
    private Integer  id;
    private String deptName;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees;

}
