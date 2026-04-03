package com.teonvioncollins.LibraryManager.controllers;

import com.teonvioncollins.LibraryManager.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/add-book")
    public String addBook(@RequestParam String title, @RequestParam String author) {
        libraryService.addBook(title, author);
        return "redirect:/library";
    }

    @PostMapping("/delete-book")
    public String deleteBook(@RequestParam Long id) {
        libraryService.deleteBook(id);
        return "redirect:/library";
    }
}
