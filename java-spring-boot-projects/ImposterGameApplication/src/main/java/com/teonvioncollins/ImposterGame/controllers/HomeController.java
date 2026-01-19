package com.teonvioncollins.ImposterGame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/host")
    public String host() {
        return "host";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @GetMapping("/example")
    public String example() {
        return "example";
    }
}
