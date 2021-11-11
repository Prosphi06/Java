package com.car.rental.management.service;

import com.car.rental.management.persistance.entity.Customer;
import com.car.rental.management.persistance.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

     CustomerRepository repo;

    /**
     * This method create new customer
     * @param customer represents request for creating new customer
     * @return string message
     */
    public Customer createCustomer(Customer customer){
        return repo.save(customer);
    }
}
