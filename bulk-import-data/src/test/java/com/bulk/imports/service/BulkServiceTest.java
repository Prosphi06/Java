package com.bulk.imports.service;

import com.bulk.imports.persistance.entity.Employee;
import com.bulk.imports.persistance.repository.EmployeeRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BulkServiceTest {

    private InputStream is;
    @Mock
    private EmployeeRepo repo;
    private BulkService service;

    @Before
    public void setUp() {
        service = new BulkService(repo);
    }

    @Test
    public void test_to_check_number_of_employees(){
       int pageNo = 0;
       int pageSize = 2;
       String sortBy = "id";

       Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Employee employee = new Employee(1, "Pro","Seen", "pro@gmail.com", "IT");
        Page<Employee> employeePage = new PageImpl<>(Collections.singletonList(employee));
        when(repo.findAll( paging)).thenReturn(employeePage);
        Page<Employee> employees = repo.findAll(paging);
        assertEquals(employees.getNumberOfElements(), 1);
    }

    @Test
    public void get_employees_paged_size(){
        int pageNo = 1;
        int pageSize = 2;
        String sortBy = "department";

        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> employeePage = new PageImpl<>(employeesList());
        when(repo.findAll( paging)).thenReturn(employeePage);
        Page<Employee> employees = repo.findAll(paging);
        assertEquals(employees.getTotalPages(), 1);
    }

    @Test
    public void test_to_sort_employees_page(){

        Pageable paging = (Pageable) PageRequest.of(1, 2, Sort.by("department"));

        Page<Employee> employeePage = new PageImpl<>(employeesList());
        when(repo.findAll( paging)).thenReturn(employeePage);
        Page<Employee> employees = repo.findAll(paging);
        assertEquals(employees.getSize(), 2);
    }

    @Test
    public void test_list_of_all_employees() {

        List<Employee> employeeList = service.getAllEmployees(1, 2, "id");
        assertThat(employeeList).isNotNull();
    }

    @Test
    public void test_get_all_employees() {

        when(service.getAllEmployees(1, 10, "id")).thenReturn(employeesList());
        Page<Employee> pagedTasks = new PageImpl(employeesList());

        assertThat(pagedTasks).isNotNull();
    }


    public List<Employee> employeesList() {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Pro","Seen", "pro@gmail.com", "IT"));
        employeeList.add(new Employee(2,"Pee", "Suna", "Peez@gmail.com","HR"));
        return employeeList;
    }

//        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "Employees.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", is);
//
//        // employeeList= (List<Employee>) mockMultipartFile.getInputStream();
//      List<Employee> employeeList = service.save(mockMultipartFile);
//      assertThat(employeeList).isNotNull();
//    }


}
