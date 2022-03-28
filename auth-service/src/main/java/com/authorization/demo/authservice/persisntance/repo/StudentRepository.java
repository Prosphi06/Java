package com.authorization.demo.authservice.persisntance.repo;

import com.authorization.demo.authservice.persisntance.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

   // Student findByFirstNameAndLastName(String firstName, String lastName);
}
