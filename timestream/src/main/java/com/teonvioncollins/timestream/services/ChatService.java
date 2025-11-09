package com.teonvioncollins.timestream.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    public final List<String> chatHistory = new ArrayList<>();

    public void addMessage(String message) {
        chatHistory.add(message);
        logger.info("Message added: {}", message);
    }

    public List<String> getChatHistory() {
        return chatHistory;
    }
}
