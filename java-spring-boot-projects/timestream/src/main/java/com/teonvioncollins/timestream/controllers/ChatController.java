package com.teonvioncollins.timestream.controllers;

import com.teonvioncollins.timestream.models.ChatSession;
import com.teonvioncollins.timestream.models.User;
import com.teonvioncollins.timestream.services.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

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
}
