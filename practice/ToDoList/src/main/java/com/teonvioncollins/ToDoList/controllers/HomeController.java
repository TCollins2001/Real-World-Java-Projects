package com.teonvioncollins.ToDoList.controllers;

import com.teonvioncollins.ToDoList.models.ListModel;
import com.teonvioncollins.ToDoList.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("tasks", itemService.getAllTasks());
        return "list";
    }

    @GetMapping("/edit-task/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ListModel task = itemService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }
}