package com.jmockit.demo.rest;

import com.jmockit.demo.persistance.model.Employee;
import com.jmockit.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Controller {

    final EmployeeService service;


    @PostMapping(value = "/create")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
        Employee createEmployee = service.createEmployee(employee);
         return ResponseEntity.ok(createEmployee);
    }

    @GetMapping(value = "/list")
    public List<Employee> employeeList(){
        List<Employee> employeeList =  service.listEmployee();
        return employeeList;
    }

    @GetMapping(value = "/list/{id}")
    public ResponseEntity<?> findOne(@PathVariable (value = "id") int id){
        // return service.findOne(id);
        try {
            return ResponseEntity.ok( service.findOne(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with id no: "+ id + " not found");
        }
    }


    @PutMapping(value = "/update/{id}")
    public Employee updateEmployee(@PathVariable (value = "id")  int id, @RequestBody Employee employee){

       return service.updateEmployee(employee,id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable (value = "id") int id){
         try {
              service.deleteEmployeeById(id);
              return ResponseEntity.ok("Employee Deleted");

         } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with id no: "+ id + " not found");
         }
    }
}
