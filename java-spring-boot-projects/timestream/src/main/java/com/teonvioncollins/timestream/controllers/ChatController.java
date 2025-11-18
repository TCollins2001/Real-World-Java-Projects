package com.teonvioncollins.timestream.controllers;

import com.teonvioncollins.timestream.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/create-chat-session")
    @ResponseBody
    public String createChatSession() {
        Long newChatId = chatService.createSession();
        return newChatId.toString();
    }

    @PostMapping("/delete-chat-session/{id}")
    @ResponseBody
    public String deleteChatSession(@PathVariable Long id) {
        chatService.deleteSession(id);
        return "deleted";
    }
}
