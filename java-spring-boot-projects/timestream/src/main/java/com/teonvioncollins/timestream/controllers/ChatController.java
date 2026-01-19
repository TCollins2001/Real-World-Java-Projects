package com.teonvioncollins.timestream.controllers;

import com.teonvioncollins.timestream.models.ChatInvite;
import com.teonvioncollins.timestream.models.ChatSession;
import com.teonvioncollins.timestream.models.User;
import com.teonvioncollins.timestream.services.ChatInviteService;
import com.teonvioncollins.timestream.services.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatInviteService chatInviteService;

    @PostMapping("/create-chat")
    @ResponseBody
    public Long createChat(@RequestParam String username, HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            throw new RuntimeException("Not logged in");
        }

        ChatSession chat = chatService.createChat(currentUser.getUsername(), username);
        return chat.getId();
    }

    @PostMapping("/delete-chat-session/{id}")
    @ResponseBody
    public void deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
    }

    @PostMapping("/invite-chat")
    @ResponseBody
    public void inviteChat(@RequestParam String username, HttpSession session) {
        User current = (User) session.getAttribute("loggedInUser");

        if (current == null) {
            throw new RuntimeException("Not logged in");
        }

        chatInviteService.sendInvite(current.getUsername(), username);
    }

    @GetMapping("/pending-invites")
    @ResponseBody
    public List<ChatInvite> getInvites(HttpSession session) {
        User current = (User) session.getAttribute("loggedInUser");
        return chatInviteService.getPendingInvites(current.getUsername());
    }

    @PostMapping("/accept-invite/{inviteId}")
    @ResponseBody
    public Long acceptInvite(@PathVariable Long inviteId) {

        ChatInvite invite = chatInviteService.acceptInvite(inviteId);

        ChatSession chat =
                chatService.createChat(invite.getFromUser(), invite.getToUser());

        return chat.getId();
    }

    @PostMapping("/decline-invite/{inviteId}")
    @ResponseBody
    public void declineInvite(@PathVariable Long inviteId) {
        chatInviteService.declineInvite(inviteId);
    }
}
