package com.authorization.demo.authservice.models.request;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {

    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private Set<String> role;
}
