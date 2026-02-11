package com.teonvioncollins.timestream.services;

import com.teonvioncollins.timestream.models.ChatParticipants;
import com.teonvioncollins.timestream.models.User;
import com.teonvioncollins.timestream.repositories.ChatRepo;
import com.teonvioncollins.timestream.repositories.LoginRepo;
import com.teonvioncollins.timestream.repositories.ParticipantRepo;
import com.teonvioncollins.timestream.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private ParticipantRepo participantRepo;

    public List<User> searchAllUsers(String query) {
        return userRepo.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }
    public void updateUserField(User user, String field, String value) {

        switch (field) {
            case "first_name" -> user.setFirst_name(value);
            case "last_name" -> user.setLast_name(value);
            case "email" -> user.setEmail(value);
            case "username" -> user.setUsername(value);
            default -> throw new IllegalArgumentException("Invalid field");
        }

        userRepo.save(user);
    }

    public void deleteUserById(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();

        loginRepo.deleteByUser(user);
        participantRepo.deleteByUsername(user.getUsername());
        chatRepo.deleteByOwner(user);

        userRepo.delete(user);
    }
}
