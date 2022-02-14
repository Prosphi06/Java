package com.user.jpa.relationship.rest;

import com.user.jpa.relationship.persistance.model.Role;
import com.user.jpa.relationship.persistance.model.User;
import com.user.jpa.relationship.service.RoleService;
import com.user.jpa.relationship.service.UserService;
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

    final UserService userService;
    final RoleService roleService;

    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

   @PostMapping("/role/create")
   public ResponseEntity<?> createRole(@RequestBody Role role) {
       return  ResponseEntity.ok(roleService.createRole(role));
   }

    @GetMapping("/role/all")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }
}
