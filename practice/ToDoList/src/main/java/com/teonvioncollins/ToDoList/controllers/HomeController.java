package com.teonvioncollins.ToDoList.controllers;
import com.teonvioncollins.ToDoList.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("items", itemService.getAllTasks());
        return "list";
    }
}