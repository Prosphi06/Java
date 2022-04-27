package com.bulk.imports.service;

import com.bulk.imports.helper.BulkHelper;
import com.bulk.imports.persistance.entity.Employee;
import com.bulk.imports.persistance.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BulkService {

    final EmployeeRepo repo;

    public void save(MultipartFile file){
        try {
            List<Employee> employeeList = BulkHelper.excelToEmployeeList(file.getInputStream());
            repo.saveAll(employeeList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<Employee> getAllEmployees(int pageNo, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> pagedResult = repo.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Employee>();
        }
    }

}
