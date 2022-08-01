package com.jmockit.demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmockit.demo.persistance.model.Employee;
import com.jmockit.demo.service.EmployeeService;
import mockit.Tested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    ObjectMapper mapper;

    private static final int id = 1;

    @Test
    public void should_return_status() throws Exception{}

    @Test
    public void happy_test_for_create_employee() throws Exception {
        //given(service.createEmployee(createEmployee())).willReturn(createEmployee());

        mvc.perform(post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(completeEmployee())))
                .andExpect(status().isOk());

    }

    @Test
    public void happy_test_for_get_employee_list() throws Exception {
       List<Employee> employeeList = new ArrayList<>();
       employeeList.add(new Employee(2,"Zew","Gudu","zee@gmail.com","IT"));
       given(service.listEmployee()).willReturn(((employeeList)));

       mvc.perform(get("/api/list")
                        .accept(MediaType.APPLICATION_JSON))
                        //.content(mapper.writeValueAsString(completeEmployee())))
                .andExpect(status().isOk());
    }

    @Test
    public void negative_test_for_empty_employee_list() throws Exception {
        mvc.perform(get("/api/list/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void happy_test_for_delete_content() throws Exception {
        given(service.findOne(id)).willReturn(Optional.ofNullable(completeEmployee()));

        mvc.perform(delete("/api/delete/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Employee Deleted"));
    }

    @Test
    public void negative_test_for_no_record_found() throws Exception {

        mvc.perform(delete("/api/delete/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Employee with id no: "+ id +" not found"));
    }

    public Employee completeEmployee(){
        return Employee.builder()
                        .id(2)
                        .firstName("Peter")
                        .lastName("Green")
                        .email("per@gmail.com")
                        .department("IT")
                        .build();
        }
}
