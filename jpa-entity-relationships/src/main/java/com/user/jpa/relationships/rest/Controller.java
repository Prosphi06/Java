package com.user.jpa.relationships.rest;

import com.user.jpa.relationships.persistance.model.Role;
import com.user.jpa.relationships.persistance.model.User;
import com.user.jpa.relationships.service.RoleService;
import com.user.jpa.relationships.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Controller {

    final RoleService roleService;
    final UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createRole(@RequestBody Role role){
        log.info("request {}", role);
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @GetMapping("/user/all")
    public List<User> getUsers() {
        return userService.listUsers();
    }

    @GetMapping("/role/all")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }
}

