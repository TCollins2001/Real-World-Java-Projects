package com.teonvioncollins.timestream.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teonvioncollins.timestream.models.MessageModel;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    private final Map<WebSocketSession, Long> sessionToChat = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String query = session.getUri().getQuery(); // sessionId=5

        if (query != null && query.startsWith("sessionId=")) {
            Long chatId = Long.parseLong(query.split("=")[1]);
            sessionToChat.put(session, chatId);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws IOException {

        MessageModel msg =
                mapper.readValue(message.getPayload(), MessageModel.class);

        Long chatId = msg.getSessionId();

        String jsonMsg = mapper.writeValueAsString(msg);

        for (WebSocketSession s : sessionToChat.keySet()) {
            if (s.isOpen() && sessionToChat.get(s).equals(chatId)) {
                s.sendMessage(new TextMessage(jsonMsg));
            }
        }
    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status) {

        sessionToChat.remove(session);
    }
}