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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-junit.properties")
public class EmployeeRepoTest {

    @MockBean
    private BulkService service;

    @Value("${app.document-root}")String documentRoot;

    @Autowired
    EmployeeRepo employeeRepo;


    @Test
    public void test_for_saving_employees() {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1, "Pro","Seen", "pro@gmail.com", "IT"));
        employeeList.add(new Employee(2,"Pee", "Suna", "Peez@gmail.com", "HR"));
        List<Employee> saveEmployees = (List<Employee>) employeeRepo.saveAll(employeeList);
        assertThat(saveEmployees).isNotNull();

    }


//    @Test
//    public void test_handleFileUpload_NoFileProvided() throws Exception{
//        MockMultipartHttpServletRequestBuilder multipartRequest =
//                MockMvcRequestBuilders.multipart("/api/files/upload");
//
//        mockMvc.perform(multipartRequest)
//                .andExpect(status().isBadRequest());
//    }

    @Test
    public void test_for_getting_employees() {
        Pageable sortedById = PageRequest.of(0, 3, Sort.by("id"));
        Page<Employee> pagedResult = employeeRepo.findAll(sortedById);
        assertThat(pagedResult.getContent().size()).isEqualTo(10);

    }
}
