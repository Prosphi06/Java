package com.postgres.learning.rest;

import com.postgres.learning.persistance.entity.Employee;
import com.postgres.learning.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class EmployeeController {

    final EmployeeService service;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(service.createEmployee(employee));
    }

    @GetMapping(value = "/list-all")
    public List<Employee> employeeList(){
        return service.listEmployees();
    }

    @GetMapping(value = "/list-one/{id}")
    public Employee findOne(@PathVariable (value = "id") int id){
        return service.findOne(id);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable (value = "id")  int id, @RequestBody Employee employee){
        return ResponseEntity.ok(service.updateEmployee(employee,id));
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteEmployee(@PathVariable (value = "id") int id){
        service.deleteEmployee(id);
        return "Employee with id no: " + id + " deleted";
    }
}
