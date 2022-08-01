package com.jmockit.demo.persistance.repo;

import com.jmockit.demo.persistance.model.Employee;
import com.jmockit.demo.service.EmployeeServiceTest;
import mockit.Injectable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo repo;

    private Employee employee;

    private EmployeeServiceTest testData = new EmployeeServiceTest();

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
    public void happy_test_to_create_employee_record() throws Exception{

        Employee results = repo.save(employee);

        assertThat(results).isNotNull();
    }

    @Test
    public void negative_test_to_create_employee_record_missing_data() throws Exception{

        Employee results = repo.save(testData.missingData());

        assertThat(results.getLastName()).isNull();
    }

    @Test
    public void test_find_all_customer(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Ana","Maak", "ana@com","CEO"));

        repo.save(employee);
        List<Employee> results = repo.findAll();

        assertThat(results).isNotNull();
        assertEquals(2, results.size());
    }

    @Test
    public void test_find_employee_byId(){
        Optional<Employee> optionalEmployee = repo.findById(1);

        assertThat(optionalEmployee).isNotNull();
        assertTrue(optionalEmployee.isPresent());
    }

    @Test
    public void test_find_customer_byId_notFound(){
        Optional<Employee> optionalEmployee = repo.findById(111);

        assertThat(optionalEmployee).isEmpty();
        assertFalse(false);
    }

    @Test
    public void test_to_update_customer_record() {
        repo.save(employee);

        repo.findById(employee.getId());
        employee.setLastName("Kana");
        employee.setDepartment("HR");

        Employee results = repo.save(employee);
        assertThat(results.getLastName()).isEqualTo("Kana");
        assertThat(results.getDepartment()).isEqualTo("HR");
    }

    @Test
    public void test_for_delete_customer_record() {
        repo.save(employee);
        repo.deleteById(employee.getId());

        Optional<Employee> optionalEmployee = repo.findById(employee.getId());
        assertThat(optionalEmployee).isEmpty();
    }
}
