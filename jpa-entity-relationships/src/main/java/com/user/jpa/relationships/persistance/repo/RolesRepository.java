package com.user.jpa.relationships.persistance.repo;

import com.user.jpa.relationships.persistance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Integer> {
}
