package com.teonvioncollins.timestream.services;

import com.teonvioncollins.timestream.models.User;
import com.teonvioncollins.timestream.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> searchAllUsers(String query) {
        return userRepo.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }
}
