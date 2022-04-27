package com.postgres.learning.rest;

import com.postgres.learning.persistance.entity.Department;
import com.postgres.learning.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/department")
public class DepartmentController {

    final DepartmentService service;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        return ResponseEntity.ok(service.createDepartment(department));
    }

    @GetMapping(value = "/list-all")
    public List<Department> departmentList(){
        return service.listDepartment();
    }

    @GetMapping(value = "/list-one/{id}")
    public Optional<Department> findOne(@PathVariable (value = "id") int id){
        return service.findOne(id);
    }
}
