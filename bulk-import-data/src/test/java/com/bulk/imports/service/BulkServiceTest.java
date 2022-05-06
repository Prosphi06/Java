package com.bulk.imports.service;

import com.bulk.imports.persistance.entity.Employee;
import com.bulk.imports.persistance.repository.EmployeeRepo;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BulkServiceTest {

    @MockBean
    private EmployeeRepo repo;
    @MockBean
    private BulkService service;

    /**
     * Failure test for retrieving empty list with page on get employees method
     */
    @Test
    public void test_to_retrieve_empty_list_page(){
       int pageNo = 0;
       int pageSize = 2;
       String sortBy = "id";

       Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Employee employee = new Employee(1, "Pro","Seen", "pro@gmail.com", "IT");
        Page<Employee> employeePage = new PageImpl<>(Collections.singletonList(employee));
        when(repo.findAll( paging)).thenReturn(employeePage);
        //Page<Employee> employees = repo.findAll(paging);
        List<Employee> employees = service.getAllEmployees(pageNo,pageSize,sortBy);
        assertThat(employees.isEmpty());
    }

    /**
     * Test for successful number of pages retrieved on get employees method
     */
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

    /**
     * Failure test for retrieving empty list on get employees method
     */
    @Test
    public void test_to_retrieve_empty_list(){

        when(repo.findAll( )).thenReturn(Collections.emptyList());
        List<Employee> employees = service.getAllEmployees(0, 2, "id");
        assertEquals(employees.size(), 0);
        assertThat(employees.isEmpty());
    }

    /**
     * Test for successful retrieving employees on get employees method
     */
    @Test
    public void test_retrieve_all_employees() {
        //given(repo.findAll()).willReturn(employeesList());
        when(repo.findAll()).thenReturn(employeesList());
        List <Employee> employeeList =  service.getAllEmployees(1, 2, "id");
        assertThat(employeeList).isNotNull();
    }

    /**
     * Test for successful retrieving employees with page on get employees method
     */
    @Test
    public void test_get_all_employees_with_page() {
        when(repo.findAll()).thenReturn(employeesList());
        List<Employee> expected = service.getAllEmployees(0, 2, "id");
        Page<Employee> pagedTasks = new PageImpl(employeesList());

        assertThat(pagedTasks).isNotNull();
    }


    public List<Employee> employeesList() {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Pro","Seen", "pro@gmail.com", "IT"));
        employeeList.add(new Employee(2,"Pee", "Suna", "Peez@gmail.com","HR"));
        return employeeList;
    }
}
