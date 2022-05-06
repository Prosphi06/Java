package com.junit.learning.persistance;

import com.junit.learning.persistance.entity.Customer;
import com.junit.learning.persistance.repository.CustomerRepo;
import com.junit.learning.service.CustomerService;
import com.junit.learning.service.CustomerServiceTest;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepoTest {

    @Autowired
    private CustomerRepo repo;
    private CustomerServiceTest serviceTest = new CustomerServiceTest();

    @After
    public void tearDown() throws Exception{
        repo.deleteAll();
    }

    /**
     * Happy test case for creating customer
     * */
    @Test
    public void test_save_customer(){
        Customer expected = repo.save(serviceTest.customerData());
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isEqualTo(1);
    }

    /**
     * Failure test case for create customer with missing data
     * */
    @Test
    public void test_save_customer_with_missing_data(){
        Customer expected = repo.save(missingData());
        //assertThat(expected.getId()).isEqualTo(0);
        assertThat(expected.getName()).isNull();
    }

    /**
     * Happy test case for retrieving all customer s
     * */
    @Test
    public void test_find_all_customer(){
       List<Customer> customerList = Arrays.asList(new Customer(1, "Peter", "pee@gmal.com","IT"));
       repo.saveAll(customerList);
        List<Customer> expected = repo.findAll();

        assertThat(expected).isNotNull();
        //assertEquals(2, expected.size());
    }

    /**
     * Happy test case for retrieving customer by id
     * */
    @Test
    public void test_find_customer_byId(){
        Optional<Customer> optionalCustomer = repo.findById(1);

        assertThat(optionalCustomer).isNotNull();
        assertTrue(optionalCustomer.isPresent());
    }

    /**
     * Failure test case for retrieving customer by id
     * */
    @Test
    public void test_find_customer_byId_notFound(){
        Optional<Customer> optionalCustomer = repo.findById(20);

        assertThat(optionalCustomer).isEmpty();
        assertFalse(optionalCustomer.isPresent());
    }

    /**
     * Happy test case for delete customer
     * */
    @Test
    public void test_for_delete_customer_endpoint() throws Exception {

        repo.save(customerData());
        repo.deleteById(customerData().getId());

        Optional<Customer> optionalCustomer = repo.findById(customerData().getId());
        assertThat(optionalCustomer).isEmpty();
    }

    public Customer missingData(){
        return Customer.builder()
                //.name("koo")
                .email("mal@gmail.com")
                .department("HR")
                .build();
    }

    public Customer customerData(){
        return Customer.builder()
                .id(1)
                .name("Lorin Malun")
                .email("mal@gmail.com")
                .department("HR")
                .build();
    }
}
