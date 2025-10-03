package com.teonvioncollins.atmapp.controllers;

import com.teonvioncollins.atmapp.services.AtmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@Controller
public class AtmController {

    @GetMapping("/homepage")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/view")
    public String view() {
        return "view";
    }
}


