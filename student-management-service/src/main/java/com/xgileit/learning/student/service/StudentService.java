package com.xgileit.learning.student.service;

import com.xgileit.learning.student.dto.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

     String addStudent( Student student);

     List<Student> listAll();

     Student findOne(int id);

     String deleteStudent(int id);

     String updateStudent(int id, Student student);
}
