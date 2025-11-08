package com.teonvioncollins.timestream.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("sign-up.html")
    public String signup() {
        return "sign-up";
    }

    @GetMapping("sign-in.html")
    public String signin() {
        return "sign-in";
    }
}
