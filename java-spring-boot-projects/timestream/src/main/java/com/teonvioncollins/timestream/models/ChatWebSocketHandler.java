package com.teonvioncollins.timestream.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teonvioncollins.timestream.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ChatService chatService;

    // Track which WebSocketSession belongs to which chat session
    private final Map<WebSocketSession, Long> sessionToChat = new HashMap<>();

    // Track active WebSocket connections
    private final Set<WebSocketSession> sessions = new HashSet<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

        // Convert JSON → MessageModel
        MessageModel msg = mapper.readValue(message.getPayload(), MessageModel.class);

        Long chatId = msg.getSessionId(); // IMPORTANT

        // Assign this WebSocket connection to its chat ID
        sessionToChat.put(session, chatId);

        // Save message to that chat history
        chatService.addMessage(msg.getMessage(), msg.getUsername(), chatId);

        // Convert back to JSON for broadcasting
        String jsonMsg = mapper.writeValueAsString(msg);

        // Broadcast ONLY to users in the same chatId
        for (WebSocketSession s : sessionToChat.keySet()) {
            if (s.isOpen() && sessionToChat.get(s).equals(chatId)) {
                s.sendMessage(new TextMessage(jsonMsg));
            }
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        sessionToChat.remove(session);
    }
}