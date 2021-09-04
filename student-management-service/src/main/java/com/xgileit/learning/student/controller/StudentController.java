package com.xgileit.learning.student.controller;

import com.xgileit.learning.student.dto.Student;
import com.xgileit.learning.student.service.StudentServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1/student")
public class StudentController {

    StudentServiceImp service = new StudentServiceImp();
    ArrayList<String> studentList=new ArrayList();

    @RequestMapping(value = "fetch/{id}", method= RequestMethod.GET)
    public String findById(@PathVariable("id") int StudentId){
        if(StudentId==2) {
            return "Student found";
        }
        return "Student not found";
    }

    @RequestMapping(value = "all/{id}", method= RequestMethod.GET)
    public List listOfStudent(@PathVariable("id") int StudentId, @RequestParam(value = "name", required = false) String name){
        if(StudentId==2) {
            studentList.add(name);
        }
        return studentList;
    }

    /**
     * End-point for creating new student
     * @param student which represent student request
     * @return student object
     */
    @PostMapping(value = "/add")
    public String addStudents(@RequestBody Student student){
        return service.addStudent(student);
    }

    /**
     * End-point for retrieving one student
     * @param id referring to a specific student id
     * @return student details request
     */
    @GetMapping(value = "/{id}")
    public Student findStudentById(@PathVariable (value = "id") int id){
        return service.findOne(id);
    }

    /**
     * End-point for retrieving all the students
     * @return  list of student
     */
    @GetMapping(value = "/list")
    public List<Student> listStudents(){
        List<Student> response = service.listAll();
        return response;
    }

    /**
     * End-point for updating one student
     * @param id referring to a specific student id
     * @param student which represent student request
     * @return student details request
     */
    @PutMapping(value = "/update/{id}")
    public String updateStudent(@PathVariable ("id") int id, @RequestBody Student student){
        String response = service.updateStudent(id, student);
        return response;
    }

    /**
     * End-point for deleting a student
     * @param id referring to a specific student id
     * @return student details request
     */
    @DeleteMapping(value = "/delete/{id}")
    public String deleteStudents(@PathVariable (value = "id") int id){
          return service.deleteStudent( id);

    }
}
