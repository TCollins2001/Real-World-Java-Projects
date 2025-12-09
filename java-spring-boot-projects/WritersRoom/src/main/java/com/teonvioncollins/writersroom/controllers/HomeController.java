package com.teonvioncollins.writersroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/sign-up")
    public String signup() {
        return "sign-up";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/notes")
    public String notes() {
        return "notes";
    }
}
