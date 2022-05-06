package com.junit.learning.service;

import com.junit.learning.exception.ResourceNotFoundException;
import com.junit.learning.persistance.entity.Customer;
import com.junit.learning.persistance.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepo repository;

    public Customer createCustomer(Customer customer){
          return repository.save(customer);
     }

        public List<Customer> listCustomer(){
            return repository.findAll();
        }

        public Customer findOne(int id){
            return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id no: " + id));
        }

        public Customer updateCustomer(Customer customer, int id){
            Customer customerInfo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id no: " + id));
            customerInfo.setEmail(customer.getEmail());
            customerInfo.setName(customer.getName());
            customerInfo.setDepartment(customer.getDepartment());
            return repository.save(customerInfo);
        }

        public void deleteCustomer(int id){
            Customer customerInfo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id no: " + id));
            repository.delete(customerInfo);
        }

    public List<Customer> getAllEmployees(int pageNo, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Customer> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Customer>();
        }
    }
}
