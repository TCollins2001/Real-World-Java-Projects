package com.teonvioncollins.ReadingRealm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/book_collection")
    public String bookCollection() {
        return "book_collection";
    }

    @GetMapping("/view")
        public String view() {
        return "view";
    }

    @GetMapping("/reader")
    public String reader() {
        return "reader";
    }
}
