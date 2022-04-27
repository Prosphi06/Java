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
       // is = service.getClass().getClassLoader().getResourceAsStream("Employees.xlsx");
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

        Employee employee = new Employee(1, "Pro","Seen", "pro@gmail.com", "IT");
        Page<Employee> employeePage = new PageImpl<>(Collections.singletonList(employee));
        when(repo.findAll( paging)).thenReturn(employeePage);
        Page<Employee> employees = repo.findAll(paging);
        assertEquals(employees.getTotalPages(), 1);
    }

    @Test
    public void test_to_sort_employees_page(){

        Pageable paging = (Pageable) PageRequest.of(1, 2, Sort.by("department"));
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Pro","Seen", "pro@gmail.com", "IT"));
        employeeList.add(new Employee(2,"Pee", "Suna", "Peez@gmail.com","HR"));

        Page<Employee> employeePage = new PageImpl<>(employeeList);
        when(repo.findAll( paging)).thenReturn(employeePage);
        Page<Employee> employees = repo.findAll(paging);
        assertEquals(employees.getSize(), 2);
       // assertThat(employees.getSort()).isEqualTo("department");
    }

    @Test
    public void test_get_all_employees() {

        Employee employee=new Employee();
        List<Employee> list=new ArrayList<Employee>();
        list.add(employee);

        when(service.getAllEmployees(1,10,"id")).thenReturn(list);
        Page<Employee> pagedTasks = new PageImpl(list);
        when(repo.findAll((Pageable) pagedTasks)).thenReturn(pagedTasks);

        list = service.getAllEmployees(1,10,"id");

       assertThat(list).isNotNull();
    }

//    @Test
//    public void test_save_all_employees_in_file() throws IOException {
//        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "Employees.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", is);
//
//        // employeeList= (List<Employee>) mockMultipartFile.getInputStream();
//      List<Employee> employeeList = service.save(mockMultipartFile);
//      assertThat(employeeList).isNotNull();
//    }
}
