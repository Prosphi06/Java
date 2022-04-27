package com.postgres.learning.service;


import com.postgres.learning.persistance.entity.Department;
import com.postgres.learning.persistance.repo.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    final DepartmentRepository repository;

    public Department createDepartment(Department department){
        return repository.save(department);
    }

    public List<Department> listDepartment(){
        return repository.findAll();
    }

    public Optional<Department> findOne(int id){
        return repository.findById(id);
    }
}
