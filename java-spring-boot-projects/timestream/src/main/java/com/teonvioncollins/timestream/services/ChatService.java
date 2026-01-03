package com.teonvioncollins.timestream.services;

import com.teonvioncollins.timestream.models.ChatSession;
import com.teonvioncollins.timestream.models.MessageModel;
import com.teonvioncollins.timestream.repositories.ChatRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    @Autowired
    private ChatRepo chatRepo;

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    public ChatSession createChat(String userA, String userB) {
        return chatRepo.save(new ChatSession(userA, userB));
    }

    public List<ChatSession> getUsersInChat(String username) {
        return chatRepo.findByUserAOrUserB(username, username);
    }

    public void deleteChat(Long id) {
        chatRepo.deleteById(id);
    }
}
