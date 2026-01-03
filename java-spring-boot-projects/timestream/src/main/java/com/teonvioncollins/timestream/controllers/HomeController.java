package com.teonvioncollins.timestream.controllers;

import com.teonvioncollins.timestream.models.ChatSession;
import com.teonvioncollins.timestream.models.User;
import com.teonvioncollins.timestream.services.ChatService;
import jakarta.servlet.http.HttpSession;
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
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/sign-in";
        }

        int chatCount = chatService
                .getUsersInChat(user.getUsername())
                .size();

        model.addAttribute("openChats", chatCount);
        return "profile";
    }

    @GetMapping("/all-chats")
    public String allChats(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/sign-in";
        }
        model.addAttribute("chats", chatService.getUsersInChat(user.getUsername()));
    return "all-chats";
}
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}