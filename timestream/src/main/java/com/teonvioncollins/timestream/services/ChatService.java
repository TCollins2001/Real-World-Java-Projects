package com.teonvioncollins.timestream.services;

import com.teonvioncollins.timestream.models.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    public final List<MessageModel> chatHistory = new ArrayList<>();

    public void addMessage(String message, String username) {
        MessageModel m = new MessageModel();
        m.setUsername(username);
        m.setMessage(message);
        chatHistory.add(m);
        logger.info("Message added {} : {}", message, username);
    }

    public List<MessageModel> getChatHistory() {
        return chatHistory;
    }
}
