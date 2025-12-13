package com.teonvioncollins.ReadingRealm.controllers;

import com.teonvioncollins.ReadingRealm.models.BookModel;
import com.teonvioncollins.ReadingRealm.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("get-books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("pageTitle", "All Books");
        return "book_collection";
    }

    @GetMapping("/find-by-genre")
    public String findBookGenres(@RequestParam String genre, Model model) {
        model.addAttribute("books", bookService.findBooksByGenre(genre));
        model.addAttribute("pageTitle", genre + " Books");
        return "book_collection";
    }

    @GetMapping("/view-book")
    public String viewBook(@RequestParam Long id, Model model) {
        model.addAttribute("books", bookService.getBookById(id));
        return "view";
    }

}
