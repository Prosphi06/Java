package com.car.rental.management.persistance.repo;

import com.car.rental.management.persistance.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the booking repository responsible for capturing data of a car transaction
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
