package com.postgres.learning.service;

import com.postgres.learning.exception.ResourceNotFoundException;
import com.postgres.learning.persistance.entity.Department;
import com.postgres.learning.persistance.entity.Employee;
import com.postgres.learning.persistance.repo.DepartmentRepository;
import com.postgres.learning.persistance.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    final EmployeeRepository repository;
    final DepartmentRepository departmentRepo;

    public Employee createEmployee(Employee employee){
       // return repository.save(employee);
//        Department dept = departmentRepo.findById(employee.getDepartment().getId()).orElse(null);
//        if (null == dept) {
//            dept = new Department();
//        }
//        dept.setDeptName(employee.getDepartment().getDeptName());
//        employee.setDepartment(dept);
        return repository.save(employee);

    }

    public List<Employee> listEmployees(){
        return repository.findAll();
    }

    public Employee findOne(int id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id no: " + id));
    }

    public Employee updateEmployee(Employee employee, int id){
        Employee employeeInfo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id no: " + id));
        employeeInfo.setEmail(employee.getEmail());
        employeeInfo.setFirstName(employee.getFirstName());
        employeeInfo.setLastName(employee.getLastName());
       return repository.save(employeeInfo);
    }

    public void deleteEmployee(int id){
        Employee employeeInfo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id no: " + id));
        repository.delete(employeeInfo);
    }
}
