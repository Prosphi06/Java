package com.spring.security.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @GetMapping("/admin")
    public String adminPage(){
        return "Welcome Admin";
    }

    @GetMapping("/user")
    public String userPage(){
        return "Welcome User";
    }

    @GetMapping("/all")
    public String allPage(){
        return "Welcome Everyone";
    }
}
