package com.teonvioncollins.ToDoList.controllers;

import com.teonvioncollins.ToDoList.models.ListModel;
import com.teonvioncollins.ToDoList.models.Priority;
import com.teonvioncollins.ToDoList.models.Status;
import com.teonvioncollins.ToDoList.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(required = false) Priority priority, @RequestParam(required = false) Status status) {

        if (priority != null & status != null) {
            model.addAttribute("tasks", itemService.getTasksByPriorityAndStatus(priority, status));
        } else if (priority != null) {
            model.addAttribute("tasks", itemService.getTasksByPriority(priority));
        } else if (status != null) {
            model.addAttribute("tasks", itemService.getTasksByStatus(status));
        } else {
            model.addAttribute("tasks", itemService.getAllTasks());
        }
        return "list";
    }

    @GetMapping("/edit-task/{id}")
    public String showEdit(@PathVariable Long id, Model model) {
        ListModel task = itemService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit";
    }
}