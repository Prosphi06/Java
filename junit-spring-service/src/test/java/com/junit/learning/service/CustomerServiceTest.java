package com.junit.learning.service;

import com.junit.learning.persistance.entity.Customer;
import com.junit.learning.persistance.repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepo repo;
    @InjectMocks
    private CustomerService service;
    private Customer customer;

    @BeforeEach
    public void setup(){
        customer = Customer.builder()
                .id(1)
                .name("Lorin Malun")
                .email("mal@gmail.com")
                .department("HR")
                .build();
    }

    /**
     * Happy test case for creating customer
     * */
    @Test
    public void test_for_saving_customer(){
        Customer customer = new Customer(1, "Peter", "pee@gmal.com","IT");

        when(repo.save(any(Customer.class))).thenReturn(customer);
        Customer savedCustomer = service.createCustomer(customer);
        verify(repo, times(1)).save(savedCustomer);
        assertThat(savedCustomer).isNotNull();
    }

    /**
     * Failure test case for create customer with missing data
     * */
    @Test
    public void test_for_saving_customer_with_missing_data() throws Exception{
        when(repo.save(any(Customer.class))).thenReturn(missingData());
        Customer savedCustomer = service.createCustomer(missingData());
        assertThat(savedCustomer.getName()).isNull();
    }

    /**
     * Happy test case for retrieving all customers
     * */
    @Test
    public void test_retrieve_all_customer(){
        Customer customer = new Customer(1, "Peter", "pee@gmal.com","IT");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customerData());

        when(repo.findAll()).thenReturn(customerList);
        List<Customer> expectedResults = service.listCustomer();

        assertThat(expectedResults).isNotNull();
        assertEquals(expectedResults,customerList);
        assertThat(expectedResults.size()).isEqualTo(2);
    }

    /**
     * Happy test case for retrieving empty list of customers
     * */
    @Test
    public void test_retrieve_all_customer_with_empty_list(){
        Customer customer = new Customer(1, "Peter", "pee@gmal.com","IT");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(missingData());

        when(repo.findAll()).thenReturn(Collections.emptyList());
        List<Customer> expectedResults = service.listCustomer();

       assertThat(expectedResults.size()).isEqualTo(0);
       assertThat(expectedResults.size()).isLessThan(1);
      // assertThat(expectedResults).
    }

    /**
     * Happy test case for retrieving customer by id
     * */
    @Test
    public void test_find_customer_byId(){
       when(repo.findById(1)).thenReturn(Optional.of(customerData()));

       Customer customer = service.findOne(1);
       assertEquals("HR", customer.getDepartment());
    }

    /**
     * Failure test case for retrieving customer by id
     * */
    @Test
    public void failure_test_find_customer_byId(){
        when(repo.findById(1)).thenReturn(Optional.of(missingData()));

        Customer customer = service.findOne(1);
        assertThat(customer.getName()).isNotEqualTo("");
    }

    /**
     * Happy test case for deleting customer
     * */
    @Test
    public void test_for_delete_customer_endpoint() throws Exception {
        int id = 1;

        //willDoNothing().given(repo).deleteById(id);
        service.deleteCustomer(id);
        Optional<Customer> optionalCustomer = repo.findById(customerData().getId());

        assertThat(optionalCustomer).isEmpty();
        //verify(repo, times(1)).deleteById(id);
    }

    /**
     * Happy test case for update customer
     * */
    @Test
    public void test_for_update_customer_endpoint() throws Exception {
       //   when(repo.findById(1)).thenReturn(Optional.of(customerData()));
        int id = 1;
        when(repo.save(customer)).thenReturn(customer);
        customer.setDepartment("Marketing");
        Customer expected = service.updateCustomer(customer, 2);

        assertThat(expected.getDepartment()).isEqualTo("Marketing");

    }

    public Customer customerData(){
        return Customer.builder()
                .id(1)
                .name("Lorin Malun")
                .email("mal@gmail.com")
                .department("HR")
                .build();
    }

    public Customer missingData(){
        return Customer.builder()
                .email("mal@gmail.com")
                .department("HR")
                .build();
    }

}
