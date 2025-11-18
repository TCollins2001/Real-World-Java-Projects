package com.teonvioncollins.timestream.services;

import com.teonvioncollins.timestream.models.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final Map<Long, List<MessageModel>> sessions = new HashMap<>();
    private Long sessionId = 1L;

    public Long createSession() {
        Long id = sessionId++;
        sessions.put(id, new ArrayList<>());
        logger.info("Created new session: {}", id);
        return id;
    }

    public void addMessage(String message, String username, Long sessionId) {
        sessions.get(sessionId).add(new MessageModel(message, username, sessionId));
        logger.info("Added message to session {}: {} ({})", sessionId, message, username);
    }

    public void deleteSession(Long sessionId) {
        logger.info("Deleted chat session {}", sessionId);
        sessions.remove(sessionId);
    }

    public List<MessageModel> getChatHistory(Long sessionId) {
        return sessions.getOrDefault(sessionId, new ArrayList<>());
    }

    public Set<Long> getAllSessionIds() {
        return sessions.keySet();
    }
}
