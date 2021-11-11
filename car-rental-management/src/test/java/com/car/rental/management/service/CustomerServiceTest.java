package com.car.rental.management.service;

import com.car.rental.management.persistance.entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @BeforeEach
    public void setUp() throws Exception{
        System.out.println("inside setup");
    }

    @AfterEach
    public void tearDown() throws Exception{
        System.out.println("inside tear down");
    }

    /**
     * Success test for creating customer details
     */
    @Test
    public void test_create_customer()throws Exception{
        Customer actual = new Customer();
        actual.setLastName("Nike");
        actual.setFirstName("Picanso");
        actual.setPhone("0734567890");
        Customer expected = customerService.createCustomer(actual);
        assertThat(expected).isNotNull();
        assertThat(expected.getCustomerId()).isGreaterThan(0);
    }

    /**
     * Failure test for creating customer with null body
     */
    @Test
    public void test_create_customer_null_body()throws Exception{
        Customer actual = new Customer();
        try {
            actual = null;
            customerService.createCustomer(actual);
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }

    /**
     * Failure test for creating customer with missing fields
     */
    @Test
    public void test_create_customer_missing_field()throws Exception{
        Customer actual = new Customer();
        actual.setFirstName("Joe");
        actual.setPhone("9087734568");

        try {
            customerService.createCustomer(actual);
        }catch (Exception e){
            System.out.println("Exception thrown");
            return;
        }
    }
}
