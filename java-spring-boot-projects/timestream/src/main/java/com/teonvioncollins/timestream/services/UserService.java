package com.teonvioncollins.timestream.services;
import com.teonvioncollins.timestream.models.ChatSession;
import com.teonvioncollins.timestream.models.User;
import com.teonvioncollins.timestream.repositories.*;
import jakarta.transaction.Transactional;
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

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ChatInviteRepo chatInviteRepo;

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

    @Transactional
    public void deleteUserById(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        String username = user.getUsername();

        List<ChatSession> ownedChats = chatRepo.findByOwner(user);
        List<Long> ownedChatIds = ownedChats.stream()
                .map(ChatSession::getId)
                .toList();

        if (!ownedChatIds.isEmpty()) {
            messageRepo.deleteByChatIdIn(ownedChatIds);
        }
        messageRepo.deleteByUsername(username);

        chatInviteRepo.deleteByFromUser(username);
        chatInviteRepo.deleteByToUser(username);

        participantRepo.deleteByUsername(username);

        chatRepo.deleteByOwner(user);

        loginRepo.deleteByUser(user);

        userRepo.delete(user);
    }
}
