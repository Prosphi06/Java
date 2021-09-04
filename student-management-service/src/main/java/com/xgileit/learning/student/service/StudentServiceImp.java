package com.xgileit.learning.student.service;

import com.xgileit.learning.student.dto.Student;
import com.xgileit.learning.student.exception.ResourceNotFoundException;

import java.util.*;

public class StudentServiceImp implements StudentService{

    Map<Integer, Student> studentMap = new HashMap<>();
   private  int studentId = 0;

    /**
     * This method create new student
     * @param student represents request for creating new student
     * @return string message
     */
    @Override
    public  String addStudent( Student student){
        student.setId(studentId +=1);
        studentMap.put(studentId,student);
        return "student add";
    }

    /**
     * This method fetch one student
     * @param id represents specific student to find
     * @return string message
     * @return Exception
     */
    @Override
    public Student findOne(int id){
        if(studentMap.containsKey(id)) {
            return studentMap.get(id);
        }else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * This method a list all students
     * @return student object
     */
    @Override
    public  List<Student> listAll(){
        return new ArrayList<>(studentMap.values());
    }

    /**
     * This method delete a student
     * @param id represents student to be deleted
     * @return string message
     * @return Exception
     */
   @Override
    public String deleteStudent(int id){
        if(studentMap.containsKey(id)) {
            studentMap.remove(id);
            return "Student deleted";
        }
        else  {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * This method update existing student
     * @param student represents request for updating student
     * @param id represent id for student
     * @return string message
     * @return Exception
     */
    @Override
    public String updateStudent(int id, Student student) {
        if (studentMap.containsKey(id)) {
            student.setId(studentId);
            studentMap.put(studentId, student);
            return "Student updated";
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
