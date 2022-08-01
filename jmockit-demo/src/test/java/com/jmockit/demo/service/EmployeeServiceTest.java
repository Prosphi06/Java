package com.jmockit.demo.service;

import com.jmockit.demo.persistance.model.Employee;
import com.jmockit.demo.persistance.repo.EmployeeRepo;

import mockit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    @Injectable
    EmployeeRepo repo;

    @Tested
    private EmployeeService service;

    private static final int id = 1;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .id(1)
                .firstName("Peter")
                .email("per@gmail.com")
                .department("IT")
                .build();
    }

    @Test
    public void happy_test_to_create_employee_record(@Injectable Employee employee) throws Exception{

        service.createEmployee(employee);

        new Verifications(){{
            repo.save(employee);
            times=1;
        }};

       assertThat(employee).isNotNull();
    }

    @Test
    public void negative_test_to_create_employee_with_missing_data() throws Exception{

        new Expectations() {{
            repo.save(missingData());
            result = missingData();
        }};

        Employee employee = service.createEmployee(missingData());

        assertThat(employee.getLastName()).isNull();
    }

    @Test
    public void find_one_employee(@Injectable Employee employee) {
       // service.createEmployee(employee);
        service.findOne(employee.getId());

        new Verifications() {{
            repo.findById(anyInt); times = 1;
        }};
    }

    @Test
    public void happy_test_for_get_one_employee() {

        new Expectations() {{
            repo.findById(anyInt);
            result = 1;
        }};

        Optional<Employee> results = service.findOne(employee.getId());

        //assertNotNull(results.get().getId());
        assertThat(results.isPresent());
        assertEquals(1,employee.getId());
    }

    @Test
    public void negative_test_for_get_one_employee() {

        new Expectations() {{
            repo.findById(anyInt);
            result = 1;
        }};

       service.findOne(missingData().getId());

       assertNull(missingData().getLastName());
   }

    @Test
    public void negative_test_to_get_empty_list() throws Exception{
        new Expectations(){{
            repo.findAll();
            result = Collections.EMPTY_LIST;
        }};

        List<Employee> employeeList = service.listEmployee();

        assertTrue(employeeList.isEmpty());
        assertThat(0).isEqualTo(0);
        assertThat(0).isLessThan(1);
    }

    @Test
    public void happy_test_to_get_employee_list() throws Exception{
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        new Expectations(){{
            repo.findAll();
            result = employeeList;
        }};

        List<Employee> Results = service.listEmployee();

        assertThat(Results).isNotNull();
        assertThat(Results.size()).isEqualTo(1);
    }

    @Test
    public void success_test_to_get_empty_list() throws Exception{
        service.listEmployee();

        new Verifications(){{
            repo.findAll();
            times=1;
        }};
    }

    @Test
    public void success_test_to_update_employee_record() throws Exception{

        new Expectations() {{
            employee.setLastName("Kanak");
            employee.setEmail("kak@gmail.com");
            repo.save(employee);
            result = employee;
        }};

        Employee employeeData = missingData();
        employeeData.setLastName("Kanak");
        employeeData.setEmail("kak@gmail.com");
        Employee employee1 = service.updateEmployee(employeeData,1);

        assertThat(employee1.getLastName()).isEqualTo("Kanak");
    }

    @Test //(expected = ResourceNotFoundException.class)
    public void happy_test_to_delete_employee_record() {

        new Expectations(){{
            repo.deleteById(anyInt);
            result = Collections.EMPTY_LIST;
        }};

        service.deleteEmployeeById(employee.getId());
        Optional<Employee> optionalCustomer = repo.findById(employee.getId());

        assertThat(optionalCustomer).isEmpty();
  }

    @Test
    public void happy_test_to_delete_employee_record_byId() {
        service.deleteEmployeeById(id);

        new Verifications() {{
            repo.deleteById(anyInt); times = 1;
        }};
    }

    public Employee missingData(){
        return Employee.builder()
                .id(1)
                .firstName("Peter")
                .email("per@gmail.com")
                .department("IT")
                .build();
    }
}
