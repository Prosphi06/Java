package com.authorization.demo.authservice.controller;

import com.authorization.demo.authservice.models.request.LoginRequest;
import com.authorization.demo.authservice.models.request.SignupRequest;
import com.authorization.demo.authservice.models.response.JwtResponse;
import com.authorization.demo.authservice.models.response.MessageResponse;
import com.authorization.demo.authservice.persisntance.entity.ERole;
import com.authorization.demo.authservice.persisntance.entity.Role;
import com.authorization.demo.authservice.persisntance.entity.User;
import com.authorization.demo.authservice.persisntance.repo.RoleRepo;
import com.authorization.demo.authservice.persisntance.repo.UserRepo;
import com.authorization.demo.authservice.security.jwt.JwtUtils;
import com.authorization.demo.authservice.service.UserDetailsImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepository;

    @Autowired
    RoleRepo roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
        log.info("login request: {}",loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new MessageResponse( "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new MessageResponse( "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User(signUpRequest.getUsername(),signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        //Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          //      .orElseThrow(() -> new RuntimeException("User Role not set."));
        //Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
        //       .orElseThrow(() -> new RuntimeException("Admin Role not set."));
        //user.setRoles(Collections.singleton(adminRole));

        /**************************************************/
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "user":
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        /*************************************************/
        //user.setRoles(Collections.singleton(userRole));
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body(new MessageResponse( "User registered successfully"));
    }
}
