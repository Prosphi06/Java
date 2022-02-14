package com.user.jpa.relationship.service;

import com.user.jpa.relationship.persistance.model.User;
import com.user.jpa.relationship.persistance.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepo;

    public User createUser(User user){
        return userRepo.save(user);
    }
}
