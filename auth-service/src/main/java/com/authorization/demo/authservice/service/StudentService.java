package com.authorization.demo.authservice.service;

import com.authorization.demo.authservice.persisntance.entity.Student;
import com.authorization.demo.authservice.persisntance.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepo;

    public List<Student> listStudent(){
        return studentRepo.findAll();
    }

    //public List<Student> list = List.of()
    public Student getStudent(int id){
        return studentRepo.findById(id).get();
    }

    public Student addStudent(Student student){
       return studentRepo.save(student);
    }

    public void deleteStudent(int id){
        studentRepo.deleteById(id);
    }

}
