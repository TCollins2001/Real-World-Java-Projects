package com.teonvioncollins.ToDoList.controllers;

import com.teonvioncollins.ToDoList.models.Priority;
import com.teonvioncollins.ToDoList.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/add-task")
    public String addTask(@RequestParam String taskName, @RequestParam LocalDate dueDate, @RequestParam Priority priority) {
        itemService.addTask(taskName, dueDate, priority);
        return "redirect:/list";
    }

    @PostMapping("/delete-task")
    public String deleteTask(@RequestParam Long id) {
        itemService.deleteTask(id);
        return "redirect:/list";
    }

    @PostMapping("/complete-task")
    public String completeTask(@RequestParam Long id) {
        itemService.completeTask(id);
        return "redirect:/list";
    }

    @PostMapping("/update-task")
    public String updateTask(@RequestParam Long id, @RequestParam String taskName, @RequestParam LocalDate dueDate, @RequestParam Priority priority) {
        itemService.updateTask(id, taskName, dueDate, priority);
        return "redirect:/list";
    }
}
