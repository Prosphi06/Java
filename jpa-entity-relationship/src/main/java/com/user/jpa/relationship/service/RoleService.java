package com.user.jpa.relationship.service;

import com.user.jpa.relationship.persistance.model.Role;
import com.user.jpa.relationship.persistance.repo.RolesRepository;
import com.user.jpa.relationship.persistance.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class RoleService {

    final RolesRepository repo;
    final UserRepository userRepo;

    public Role createRole(Role role){
        log.info("roles {}", role);
        return  repo.save(role);
    }

    @GetMapping("/role/all")
    public List<Role> getRoles() {
        return repo.findAll();
    }
}
