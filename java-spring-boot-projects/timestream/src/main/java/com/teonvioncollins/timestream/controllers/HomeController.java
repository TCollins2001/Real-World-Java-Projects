package com.teonvioncollins.timestream.controllers;

import com.teonvioncollins.timestream.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/sign-up")
    public String signup() {
        return "sign-up";
    }

    @GetMapping("/sign-in")
    public String signin() {
        return "sign-in";
    }

    @GetMapping("/chatroom")
    public String chatroom(@RequestParam Long sessionId, Model model) {
        model.addAttribute("sessionId", sessionId);
        return "chatroom";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/all-chats")
    public String allchats(Model model) {
        model.addAttribute("chatIds", chatService.getAllSessionIds());
    return "all-chats";
}
}