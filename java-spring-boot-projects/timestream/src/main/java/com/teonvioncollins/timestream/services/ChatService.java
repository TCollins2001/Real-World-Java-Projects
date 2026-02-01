package com.teonvioncollins.timestream.services;

import com.teonvioncollins.timestream.models.ChatSession;
import com.teonvioncollins.timestream.models.MessageModel;
import com.teonvioncollins.timestream.repositories.ChatRepo;
import com.teonvioncollins.timestream.repositories.ParticipantRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private ParticipantRepo participantRepo;

    private static final Logger logger =
            LoggerFactory.getLogger(ChatService.class);

    public ChatSession createChat(String createdBy) {
        ChatSession chat = new ChatSession();
        chat.setCreatedBy(createdBy);
        return chatRepo.save(chat);
    }

    public List<ChatSession> getChatsForUser(String username) {
        return chatRepo.findChatsByUsername(username);
    }

    public Map<String, Object> getChatPreview(ChatSession chat) {
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", chat.getId());
        dto.put("participants", chatRepo.findParticipants(chat.getId()));
        return dto;
    }


    public void deleteChat(Long id) {
        chatRepo.deleteById(id);
    }
}
