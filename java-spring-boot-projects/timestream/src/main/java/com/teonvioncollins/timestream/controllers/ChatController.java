package com.teonvioncollins.timestream.controllers;

import com.teonvioncollins.timestream.component.ChatWebSocketHandler;
import com.teonvioncollins.timestream.models.*;
import com.teonvioncollins.timestream.repositories.ChatInviteRepo;
import com.teonvioncollins.timestream.repositories.ChatRepo;
import com.teonvioncollins.timestream.repositories.MessageRepo;
import com.teonvioncollins.timestream.repositories.ParticipantRepo;
import com.teonvioncollins.timestream.services.ChatInviteService;
import com.teonvioncollins.timestream.services.ChatService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ChatWebSocketHandler chatWebSocketHandler;

    @PostMapping("/create-chat")
    @ResponseBody
    public Long createChat(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            throw new RuntimeException("Not logged in");
        }
        ChatSession chat = chatService.createChat(user);
        participantRepo.save(new ChatParticipants(chat.getId(), user.getUsername()));

        return chat.getId();
    }

    @GetMapping("/chat-messages")
    @ResponseBody
    public List<MessageModel> getMessages(@RequestParam Long sessionId) {
        return messageRepo.findByChatIdOrderByIdAsc(sessionId);
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
    @Transactional
    public void deleteChat(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return;

        participantRepo.deleteByChatIdAndUsername(id, user.getUsername());

        List<ChatParticipants> remaining = participantRepo.findByChatId(id);
        if (remaining.isEmpty()) {
            chatService.deleteChat(id);
        }
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

    @PostMapping("/send-invite")
    @ResponseBody
    @Transactional
    public Long sendInvite(@RequestBody InviteRequest request, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            throw new RuntimeException("Not logged in");
        }

        List<String> invitees = request.getInvitees();

        ChatSession chat = chatService.createChat(user);
        participantRepo.save(new ChatParticipants(chat.getId(), user.getUsername()));

        for (String invitee : invitees) {
            try {
                chatInviteService.sendInvite(chat.getId(), user.getUsername(), invitee);
            } catch (Exception e) {
                System.err.println("Invite failed for " + invitee + ": " + e.getMessage());
            }
        }

        return chat.getId();
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
    public Map<String, Object> acceptInvite(@PathVariable Long inviteId, HttpSession session) {
        User current = (User) session.getAttribute("loggedInUser");
        if (current == null) {
            throw new RuntimeException("Not logged in");
        }

        ChatInvite invite = chatInviteService.acceptInvite(inviteId);

        boolean wasActive = participantRepo
                .existsByChatIdAndUsernameAndActiveTrue(invite.getChatId(), current.getUsername());

        if (!wasActive) {
            participantRepo.save(new ChatParticipants(invite.getChatId(), current.getUsername()));

            MessageModel systemMsg = new MessageModel(
                    invite.getChatId(),
                    "System",
                    current.getUsername() + " joined the chat"
            );
            systemMsg.setSystem(true);

            messageRepo.save(systemMsg);
            chatWebSocketHandler.broadcastSystemMessage(invite.getChatId(), systemMsg);
        }

        ChatSession chat = chatRepo.findById(invite.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        return chatService.getChatPreview(chat);
    }

    @PostMapping("/rename-chat")
    @ResponseBody
    public void renameChat(
            @RequestParam Long chatId,
            @RequestParam(required = false) String name,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) throw new RuntimeException("Not logged in");

        chatService.updateChatroomName(chatId, name);
    }


    @GetMapping("/chat-participants")
    @ResponseBody
    public List<Map<String, Object>> getChatParticipants(
            @RequestParam Long sessionId,
            HttpSession session
    ) {
        User current = (User) session.getAttribute("loggedInUser");
        if (current == null) {
            return List.of();
        }

        return participantRepo.findByChatId(sessionId).stream()
                .map(p -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("username", p.getUsername());
                    m.put("active", p.isActive());
                    return m;
                })
                .toList();
    }

    @PostMapping("/decline-invite/{inviteId}")
    @ResponseBody
    public void declineInvite(@PathVariable Long inviteId) {
        chatInviteService.declineInvite(inviteId);
    }

    @PostMapping("/leave-chat")
    @ResponseBody
    @Transactional
    public void leaveChat(@RequestParam Long chatId, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return;

        participantRepo.markLeft(chatId, user.getUsername());

        MessageModel systemMsg = new MessageModel(
                chatId,
                "System",
                user.getUsername() + " left the chat"
        );
        systemMsg.setSystem(true);
        messageRepo.save(systemMsg);

        chatWebSocketHandler.broadcastSystemMessage(chatId, systemMsg);
    }
}
