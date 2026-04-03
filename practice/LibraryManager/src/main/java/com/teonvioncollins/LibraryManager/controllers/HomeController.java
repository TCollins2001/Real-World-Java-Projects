package com.teonvioncollins.LibraryManager.controllers;
import com.teonvioncollins.LibraryManager.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/library")
    public String library(Model model) {
        model.addAttribute("books", libraryService.getAllBooks());
        model.addAttribute("totals", libraryService.getTotalBooks());
        return "library";
    }
}
