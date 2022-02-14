package com.user.jpa.relationship.persistance.repo;

import com.user.jpa.relationship.persistance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Integer> {
}
