package com.authorization.demo.authservice.persisntance.repo;

import com.authorization.demo.authservice.persisntance.entity.ERole;
import com.authorization.demo.authservice.persisntance.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(ERole name);
}
