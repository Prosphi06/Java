package com.user.jpa.relationships.service;

import com.user.jpa.relationships.persistance.model.User;
import com.user.jpa.relationships.persistance.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepo;

    public User createUser(User user){
        return userRepo.save(user);
    }

    public List<User> listUsers(){
        return userRepo.findAll();
    }
}
