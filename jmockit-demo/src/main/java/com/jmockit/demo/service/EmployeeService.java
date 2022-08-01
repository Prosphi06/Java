package com.jmockit.demo.service;

import com.jmockit.demo.exception.ResourceNotFoundException;
import com.jmockit.demo.persistance.model.Employee;
import com.jmockit.demo.persistance.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmployeeService {

    final EmployeeRepo repository;

    public Employee createEmployee(Employee employee){
        log.info("Inside Service {}",employee);
        return repository.save(employee);
    }

    public List<Employee> listEmployee(){
        List<Employee> employeeList = repository.findAll();
        log.info("Inside Service {}", employeeList);
            return employeeList;
    }

    public Optional<Employee> findOne(Integer id){
        try{
            log.info("Inside Service :", id);
            return repository.findById(id);
        } catch (Exception ex) {
            log.error("Exception thrown : ", ex);
            throw new ResourceNotFoundException();
        }
    }

    public Employee updateEmployee(Employee employee, int id){
        try{
            Employee employeeInfo = repository.findById(id).get();
            employeeInfo.setFirstName(employee.getFirstName());
            employeeInfo.setLastName(employee.getLastName());
            employeeInfo.setEmail(employee.getEmail());
            employeeInfo.setDepartment(employee.getDepartment());
            log.info("Inside service {}",employee);
            return repository.save(employeeInfo);
    } catch (Exception ex) {
            log.error("Exception thrown : ", ex);
            throw new ResourceNotFoundException();
        }
    }

//    public void deleteEmployee(int id){
//        try{
//            Employee employeeInfo = repository.findById(id).get();
//            log.info("Inside Service {}",id);
//             repository.delete(employeeInfo);
//    } catch (Exception ex) {
//            log.error("Exception thrown : ", ex);
//            throw new ResourceNotFoundException();
//        }
//    }

    public void deleteEmployeeById(int id){
        try{
            log.info("Inside Service {}",id);
            repository.deleteById(id);
            //repository.delete(employeeInfo);
        } catch (Exception ex) {
            log.error("Exception thrown : ", ex);
            throw new ResourceNotFoundException();
        }
    }
}
