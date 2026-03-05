package com.teonvioncollins.ToDoList.controllers;

import com.teonvioncollins.ToDoList.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/add-task")
    public String addTask(@RequestParam String taskName) {
        itemService.addTask(taskName);
        return "redirect:/list";
    }

    @PostMapping("/delete-task")
    public String deleteTask(@RequestParam Long id) {
        itemService.deleteTask(id);
        return "redirect:/list";
    }
}
