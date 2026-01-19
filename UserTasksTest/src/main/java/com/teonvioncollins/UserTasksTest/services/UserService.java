package com.teonvioncollins.UserTasksTest.services;

import com.teonvioncollins.UserTasksTest.models.UserModel;
import com.teonvioncollins.UserTasksTest.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel createUser(String username, String email) {
        UserModel user = new UserModel(username, email);
        return userRepository.save(user);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel getSingleUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }
}
