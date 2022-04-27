package com.bulk.imports.rest;

import com.bulk.imports.dto.ResponseMessage;
import com.bulk.imports.helper.BulkHelper;
import com.bulk.imports.persistance.entity.Employee;
import com.bulk.imports.service.BulkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Controller {

    final BulkService service;

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
        String message = "";
        if(BulkHelper.hasExcelFormat(file)){
            try{
                service.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = "department") String sortBy) {
            return new ResponseEntity<List<Employee>>(service.getAllEmployees(pageNo, pageSize, sortBy), new HttpHeaders(), HttpStatus.OK);
    }
}
