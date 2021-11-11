package com.car.rental.management.persistance.repo;

import com.car.rental.management.persistance.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * This is the customer repository responsible for capturing data of the customer transaction
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
