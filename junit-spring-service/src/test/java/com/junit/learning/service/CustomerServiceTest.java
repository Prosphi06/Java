package com.junit.learning.service;

import com.junit.learning.persistance.entity.Customer;
import com.junit.learning.persistance.repository.CustomerRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepo repo;
    @InjectMocks
    private CustomerService service;

    @Test
    public void test_for_saving_customer(){
        Customer customer = new Customer(1, "Peter", "pee@gmal.com","IT");

        when(repo.save(any(Customer.class))).thenReturn(customer);
        Customer savedCustomer = service.createCustomer(customer);
        verify(repo, times(1)).save(savedCustomer);
        assertThat(savedCustomer).isNotNull();
    }

    @Test
    public void test_for_saving_customer_with_missing_data() throws Exception{
        when(repo.save(any(Customer.class))).thenReturn(missingData());
        Customer savedCustomer = service.createCustomer(missingData());
        assertThat(savedCustomer.getName()).isNull();
    }

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

    @Test
    public void test_find_customer_byId(){
       when(repo.findById(1)).thenReturn(Optional.of(customerData()));

       Customer customer = service.findOne(1);
       assertEquals("HR", customer.getDepartment());
    }

    @Test
    public void failure_test_find_customer_byId(){
        when(repo.findById(1)).thenReturn(Optional.of(missingData()));

        Customer customer = service.findOne(1);
        assertThat(customer.getName()).isNotEqualTo("Lorin Malun");
    }

    @Test
    public void test_for_accessing_delete_customer_endpoint() throws Exception {
        int id = 1;

        willDoNothing().given(repo).deleteById(id);
        service.deleteCustomer(id);

        verify(repo, times(1)).deleteById(id);
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
