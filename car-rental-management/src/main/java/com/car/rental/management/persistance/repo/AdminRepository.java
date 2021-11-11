package com.car.rental.management.persistance.repo;

import com.car.rental.management.persistance.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the admin repository responsible for capturing data of a admin details
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
