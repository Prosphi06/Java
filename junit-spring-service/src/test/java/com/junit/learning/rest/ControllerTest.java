package com.junit.learning.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junit.learning.persistance.entity.Customer;
import com.junit.learning.persistance.repository.CustomerRepo;
import com.junit.learning.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService service;

    @Mock
    private CustomerRepo repo;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void test_for_accessing_create_customer_endpoint() throws Exception {

        when(service.createCustomer(any(Customer.class))).thenReturn(customerData());
        ResultActions expectedResults = mvc.perform(post("/api/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(customerData())));

        expectedResults.andDo(print())
                .andExpect(status().isOk());
//                .andExpect( jsonPath("$.id", is(customerData().getId())))
//                .andExpect((ResultMatcher) jsonPath("$.name", is(customerData().getName())))
//                .andExpect((ResultMatcher) jsonPath("$.email", is(customerData().getEmail())))
//                .andExpect((ResultMatcher) jsonPath("$.department", is(customerData().getDepartment())));
    }

    @Test
    public void failure_test_for_accessing_create_customer_endpoint() throws Exception {

        when(service.createCustomer(any(Customer.class))).thenReturn(customerData());
        ResultActions expectedResults = mvc.perform(post("/apis/create/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(customerData())));

        expectedResults.andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void test_for_accessing_retrieve_customer_endpoint() throws Exception {
        //given --> precondition or setup
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer(2, "Lulu", "lu@gmail.com", "HR");
        customerList.add(customer);
        customerList.add(customerData());
        when(service.listCustomer()).thenReturn(customerList);

        //when --> action or the behaviour that we are going to test
        ResultActions response = mvc.perform(get("/api/list-all")
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then --> verify the output
        response.andExpect(status().isOk())
                .andDo(print());
                //.andExpect(jsonPath("$.size()", is(customerList.size())));
    }

    @Test
    public void failure_test_for_accessing_retrieve_customer_endpoint() throws Exception {

        when(service.listCustomer()).thenReturn(Collections.emptyList());

        //when --> action or the behaviour that we are going to test
        ResultActions response = mvc.perform(get("/api/list-all"));

        //then --> verify the output
        response.andExpect(status().isOk())
                .andExpect(content().json("[]"))
                .andDo(print());
        //.andExpect(jsonPath("$.size()", is(customerList.size())));
    }

    @Test
    public void test_for_accessing_retrieve_customer_byId_endpoint() throws Exception {
       int id = 1;
       when(service.findOne(id)).thenReturn(customerData());

       ResultActions response = mvc.perform(get("/api/list-one/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

       response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void failure_test_for_accessing_retrieve_customer_byId_endpoint() throws Exception {
        int id = 1;
       // when(service.findOne(id)).thenReturn(inv);
        //willReturn(Optional.empty()).given(service.findOne(id));
        ResultActions response = mvc.perform(post("/api/list-one/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        response.andExpect(status().is5xxServerError())
                .andDo(print());
    }

    @Test
    public void test_for_accessing_update_customer_endpoint() throws Exception {
        int id = 1;

//        when(repo.findById(1)).thenReturn(Optional.of(customerData()));
//        when(service.updateCustomer(any(Customer.class),id)).thenReturn(updateData());
//
        ResultActions response = mvc.perform(put("/api/update/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(updateData())));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void failure_test_for_accessing_update_customer_endpoint() throws Exception {
        int id = 1;

        ResultActions response = mvc.perform(get("/api/update/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(updateData())));

        response.andExpect(status().is5xxServerError())
                .andDo(print());
    }

    @Test
    public void test_for_accessing_delete_customer_endpoint() throws Exception {
        int id = 1;

        willDoNothing().given(service).deleteCustomer(id);
        ResultActions response = mvc.perform(delete("/api/delete/{id}",id));

        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void failure_test_for_accessing_delete_customer_endpoint() throws Exception {
        int id = 1;

        willDoNothing().given(service).deleteCustomer(id);
        ResultActions response = mvc.perform(post("/api/delete/{id}",id));

        response.andExpect(status().is5xxServerError())
                .andDo(print());
    }

    public Customer customerData(){
        return Customer.builder()
                .id(1)
                .name("Pinky")
                .email("pk@gmail.com")
                .department("IT")
                .build();
    }

    public Customer updateData(){
        return Customer.builder()
                .id(1)
                .name("Pinkky")
                .email("pkk@gmail.com")
                .department("IT")
                .build();
    }
}
