package com.bulk.imports.rest;

import com.bulk.imports.persistance.entity.Employee;
import com.bulk.imports.persistance.repository.EmployeeRepo;
import com.bulk.imports.service.BulkService;
import static com.bulk.imports.PageableAssert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;
import java.util.Collections;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    private InputStream is;
    @MockBean
    private EmployeeRepo repo;
    @MockBean
    private BulkService service;
    @Autowired
    private Controller controller;
    @Autowired
    MockMvc mvc;
    private JacksonTester<Employee> employeeJacksonTester;

    @Before
    public void init() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        is = controller.getClass().getClassLoader().getResourceAsStream("Employees.xlsx");
       // JacksonTester.initFields(this, new ObjectMapper());
   }


    @Test
    public void test_for_upload_endpoint() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "Employees.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", is);
        MvcResult results = mvc.perform(MockMvcRequestBuilders.multipart("/api/upload")
                        .file(mockMultipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is(200))
                .andReturn();
        Assert.assertEquals(200, results.getResponse().getStatus());
        Assert.assertNotNull(results.getResponse().getContentAsString());
        // Assert.assertEquals("Employees.xlsx", results.getResponse().getContentAsString());
    }

    @Test
    public void test_for_upload_endpoint_with_wrong_url() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "Employees.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", is);
        mvc.perform(MockMvcRequestBuilders.multipart("/api/uploads/")
                        .file(mockMultipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void test_for_upload_endpoint_with_no_file() throws Exception {
        mvc.perform(MockMvcRequestBuilders.multipart("/api/upload")
                        //.file(mockMultipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                //.andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void test_for_accessing_employees_endpoint() throws Exception {
        mvc.perform(get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void failure_tests_for_createTransfer_endPoint() throws Exception {
        mvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isMethodNotAllowed());

        mvc.perform(get("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
    }

    @Test
    public void test_for_paging_endpoint() throws Exception {
        Employee employee = new Employee(1, "Pro", "Seen", "pro@gmail.com", "IT");
        Page<Employee> page = new PageImpl<>(Collections.singletonList(employee));
       // given(service.getAllEmployees(any(),any(),any())).willReturn(page);
        //when(service.getAllEmployees(anyInt(),anyInt(),anyString())).thenReturn(page);
        mvc.perform(get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.content[0].department", is(employee.getDepartment())));
    }

    @Test
    public void test_for_accessing_pageable_endpoint() throws Exception {
        mvc.perform(get("/api/employees")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .param("sort", "department")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // .andExpect((ResultMatcher) jsonPath("$.content[0].department", is(employee.getDepartment())));
        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(repo).findAll(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(1);
        assertThat(pageable).hasPageSize(10);
        assertThat(pageable).hasSort("id", Sort.Direction.ASC);
        // assertThat(pageable).sor("department", Sort.Direction.ASC);

    }
}