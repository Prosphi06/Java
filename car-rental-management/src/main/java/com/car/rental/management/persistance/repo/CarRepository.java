package com.car.rental.management.persistance.repo;

import com.car.rental.management.persistance.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the car repository responsible for capturing data of a car transaction
 */
@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
}
