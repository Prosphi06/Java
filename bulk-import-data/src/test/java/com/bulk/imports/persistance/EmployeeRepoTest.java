package com.bulk.imports.persistance;

import com.bulk.imports.persistance.entity.Employee;
import com.bulk.imports.persistance.repository.EmployeeRepo;
import com.bulk.imports.service.BulkService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-junit.properties")
public class EmployeeRepoTest {

    @Autowired
    EmployeeRepo employeeRepo;

    /**
      * Test for successful saving of employee on save employees method
      *
      */
    @Test
    public void test_for_saving_employees() {

        List<Employee> employeeList = (List<Employee>) employeeRepo.saveAll(employeesList());
        assertThat(employeeList).isNotNull();
    }

    /**
      * Test for successful retrieving employees with paging
      */
    @Test
    public void test_for_retrieve_employees() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by("id"));
        employeeRepo.saveAll(employeesList());
        Page<Employee> employeeList = employeeRepo.findAll(pageable);
        assertThat(employeeList).isNotNull();
    }
//    @Test
//    public void test_returned_page_size() {
//        assertThat(
//                employeeRepo
//                        .findAll(PageRequest.of(0, 10))
//                        .getContent()
//                        .size())
//                .isEqualTo(10);
//    }

     /**
      * Test for successful check employees size
      */
     @Test
     public void test_for_getting_employees() {
        Pageable sortedById = PageRequest.of(0, 2, Sort.by("id"));
        employeeRepo.saveAll(employeesList());
        Page<Employee> pagedResult = employeeRepo.findAll(sortedById);
        assertThat(pagedResult.getContent().size()).isEqualTo(2);

    }

    public List<Employee> employeesList() {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Pro", "Seen", "pro@gmail.com", "IT"));
        employeeList.add(new Employee(2, "Pee", "Suna", "Peez@gmail.com", "HR"));
        return employeeList;
    }
}
