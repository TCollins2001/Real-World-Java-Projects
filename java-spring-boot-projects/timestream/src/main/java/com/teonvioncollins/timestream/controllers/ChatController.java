package com.teonvioncollins.timestream.controllers;

import com.teonvioncollins.timestream.models.ChatInvite;
import com.teonvioncollins.timestream.models.ChatParticipants;
import com.teonvioncollins.timestream.models.ChatSession;
import com.teonvioncollins.timestream.models.User;
import com.teonvioncollins.timestream.repositories.ChatInviteRepo;
import com.teonvioncollins.timestream.repositories.ChatRepo;
import com.teonvioncollins.timestream.repositories.ParticipantRepo;
import com.teonvioncollins.timestream.services.ChatInviteService;
import com.teonvioncollins.timestream.services.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatInviteService chatInviteService;

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private ParticipantRepo participantRepo;

    @Autowired
    private ChatInviteRepo chatInviteRepo;

    @PostMapping("/create-chat")
    @ResponseBody
    public Long createChat(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            throw new RuntimeException("Not logged in");
        }
        ChatSession chat = new ChatSession();
        chat.setCreatedBy(user.getUsername());
        chatRepo.save(chat);

        participantRepo.save(new ChatParticipants(chat.getId(), user.getUsername()));

        return chat.getId();
    }

    @GetMapping("/chat-previews")
    @ResponseBody
    public List<Map<String, Object>> chatPreviews(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return List.of();
        }

            return chatService
                    .getChatsForUser(user.getUsername())
                    .stream()
                    .map(chatService::getChatPreview)
                    .toList();
    }

    @PostMapping("/delete-chat-session/{id}")
    @ResponseBody
    public void deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
    }

    @PostMapping("/invite-chat")
    @ResponseBody
    public void inviteChat(
            HttpSession session,
            @RequestParam Long chatId,
            @RequestParam String username
    ) {
        User from = (User) session.getAttribute("loggedInUser");

        if (from == null) {
            throw new RuntimeException("Not logged in");
        }

        chatInviteService.sendInvite(chatId, from.getUsername(), username);
    }


    @GetMapping("/pending-invites")
    @ResponseBody
    public List<ChatInvite> getInvites(HttpSession session) {
        User current = (User) session.getAttribute("loggedInUser");

        if (current == null) {
            return List.of();
        }

        return chatInviteService.getPendingInvites(current.getUsername());
    }


    @PostMapping("/accept-invite/{inviteId}")
    @ResponseBody
    public Long acceptInvite(@PathVariable Long inviteId, HttpSession session) {

        User current = (User) session.getAttribute("loggedInUser");
        if (current == null) {
            throw new RuntimeException("Not logged in");
        }

        ChatInvite invite = chatInviteService.acceptInvite(inviteId);

        if(!participantRepo.existsByChatIdAndUsername(invite.getChatId(), current.getUsername())) {

            participantRepo.save(new ChatParticipants(invite.getChatId(), current.getUsername()));
        }

        return invite.getChatId();
    }

    @PostMapping("/decline-invite/{inviteId}")
    @ResponseBody
    public void declineInvite(@PathVariable Long inviteId) {
        chatInviteService.declineInvite(inviteId);
    }
}
