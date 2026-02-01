package com.teonvioncollins.timestream.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teonvioncollins.timestream.models.MessageModel;
import com.teonvioncollins.timestream.repositories.ParticipantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    private final Map<WebSocketSession, Long> sessionToChat = new java.util.concurrent.ConcurrentHashMap<>();

    @Autowired
    private ParticipantRepo participantRepo;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = Objects.requireNonNull(session.getUri()).getQuery();
        Map<String, String> params = Arrays.stream(query.split("&"))
                .map(p -> p.split("=", 2))
                .filter(p -> p.length == 2)
                .collect(Collectors.toMap(p -> p[0], p -> p[1]));

        if (!params.containsKey("sessionId") || !params.containsKey("username")) {
            session.close();
            return;
        }

        Long chatId = Long.parseLong(params.get("sessionId"));
        String username = URLDecoder.decode(params.get("username"), StandardCharsets.UTF_8);

        boolean allowed =
                participantRepo.existsByChatIdAndUsername(chatId, username);

        if (!allowed) {
            session.close();
            return;
        }

        sessionToChat.put(session, chatId);

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