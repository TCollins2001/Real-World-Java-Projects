package com.teonvioncollins.listsapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListsController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
