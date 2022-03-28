package com.authorization.demo.authservice.controller;

import com.authorization.demo.authservice.persisntance.entity.Student;
import com.authorization.demo.authservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/students")
public class StudentController {

    final StudentService studentService;

    @GetMapping("/all")
        //public String allAccess() {  return "Public Content.";
        public List<Student> fetchAll(){
            return  studentService.listStudent();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() { return "User Content.";}

    @PostMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
         return ResponseEntity.ok(studentService.addStudent(student));
    }

        @GetMapping("/admin")
        @PreAuthorize("hasRole('ADMIN')")
        public String adminAccess() {
            return "Admin Board.";
        }

        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping(value = "/admin/{id}")
        public ResponseEntity<?> deleteStudents(@PathVariable (value = "id") int id){
            studentService.deleteStudent( id);
        return ResponseEntity.ok("Student with id: " + id + " deleted");
        }

}