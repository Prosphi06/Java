package com.bulk.imports.persistance;

import com.bulk.imports.persistance.entity.Employee;
import com.bulk.imports.persistance.repository.EmployeeRepo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-junit.properties")
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepo employeeRepo;

    /**
     * Test for success saving an employee with missing details
     */
    @Test
    public void test_for_saving_employees () {
        List<Employee> employeeList = (List<Employee>) employeeRepo.saveAll(employeesList());
        Assertions.assertThat(employeeList).isNotNull();
    }

    /**
     * Test for fail saving an employee with missing details
     */
    @Test
    public void test_for_saving_employees_with_missing_datails () {
        employeesList().add(missingDetails());
        List<Employee> employeeList = (List<Employee>) employeeRepo.saveAll(employeesList());
        Assertions.assertThat(employeeList).isEmpty();
    }
    /**
     * Test for successful retrieving employees with paging
     */
    @Test
    public void test_for_retrieve_employees () {
        //Pageable pageable = PageRequest.of(0, 3, Sort.by("id"));
        employeeRepo.saveAll(employeesList());
        List<Employee> employeeList = employeeRepo.findAll();
        Assertions.assertThat(employeeList).isNotNull();
    }

    /**
     * Test for successful check employees size
     */
    @Test
    public void test_for_getting_employees () {
        Pageable sortedById = PageRequest.of(0, 2, Sort.by("id"));
        employeeRepo.saveAll(employeesList());
        Page<Employee> pagedResult = employeeRepo.findAll(sortedById);
        Assertions.assertThat(pagedResult.getContent().size()).isEqualTo(2);

    }


    public List<Employee> employeesList () {
            List<Employee> employeeList = new ArrayList<Employee>();
            employeeList.add(new Employee(1, "Pro", "Seen", "pro@gmail.com", "IT"));
            employeeList.add(new Employee(2, "Pee", "Suna", "Peez@gmail.com", "HR"));
            return employeeList;
        }

       public Employee missingDetails() {
           return Employee.builder()
                    .firstName("Pro")
                    .lastName("Suna")
                    .email("pro@gmail.com")
                    .department("IT")
                    .build();
       }
    }
