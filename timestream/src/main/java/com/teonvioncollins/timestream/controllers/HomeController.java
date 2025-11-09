package com.teonvioncollins.timestream.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/sign-up")
    public String signup() {
        return "sign-up";
    }

    @GetMapping("/sign-in")
    public String signin() {
        return "sign-in";
    }

    @GetMapping("/chatroom")
    public String chatroom() {
        return "chatroom";
    }
}
