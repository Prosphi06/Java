package com.junit.learning.rest;

import com.junit.learning.persistance.entity.Customer;
import com.junit.learning.service.CustomerService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Controller {

    final CustomerService service;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(service.createCustomer(customer));
    }

    @GetMapping(value = "/list-all")
    public List<Customer> customerList(){
        return service.listCustomer();
    }

    @GetMapping(value = "/list-one/{id}")
    public Customer findOne(@PathVariable (value = "id") int id){
        return service.findOne(id);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable (value = "id")  int id, @RequestBody Customer employee){
        return ResponseEntity.ok(service.updateCustomer(employee,id));
    }

//    @PutMapping(value = "/update")
//    public ResponseEntity<?> updateCustomer( @RequestBody Customer employee){
//        return ResponseEntity.ok(service.updateCustomer(employee));
//    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteCustomer(@PathVariable (value = "id") int id){
        service.deleteCustomer(id);
        return "Customer with id no: " + id + " deleted";
    }
}
