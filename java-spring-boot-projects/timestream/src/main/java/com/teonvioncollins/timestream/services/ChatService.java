package com.teonvioncollins.timestream.services;

import com.teonvioncollins.timestream.models.ChatParticipants;
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
        Map<String, Object> preview = new HashMap<>();

        List<String> activeParticipants = participantRepo
                .findByChatId(chat.getId())
                .stream()
                .filter(ChatParticipants::isActive)
                .map(ChatParticipants::getUsername)
                .distinct()
                .toList();

        preview.put("id", chat.getId());
        preview.put("participants", activeParticipants);
        preview.put("customRoomName", chat.getCustomRoomName());

        return preview;
    }

    public void deleteChat(Long id) {
        chatRepo.deleteById(id);
    }

    public void updateChatroomName(Long chatId, String newName) {
        ChatSession chat = chatRepo.findById(chatId).orElseThrow();
        chat.setCustomRoomName(
                (newName == null || newName.isBlank()) ? null : newName
        );
        chatRepo.save(chat);
    }

    public long countOpenChatsForUser(String username) {
        List<ChatParticipants> myParticipants =
                participantRepo.findByUsername(username);

        return myParticipants.stream()
                .filter(ChatParticipants::isActive)
                .map(ChatParticipants::getChatId)
                .distinct()
                .filter(chatId ->
                        participantRepo.findByChatId(chatId).stream()
                                .anyMatch(p ->
                                        p.isActive() &&
                                                !p.getUsername().equals(username)
                                )
                )
                .count();
    }
}
