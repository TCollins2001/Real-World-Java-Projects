package com.teonvioncollins.ExpenseTracker.controllers;

import com.teonvioncollins.ExpenseTracker.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add-expense")
    public String addExpense(@RequestParam String itemDesc, @RequestParam double itemAmount) {
        expenseService.addExpense(itemDesc, itemAmount);
        return "redirect:/expenses";
    }

    @PostMapping("/delete-expense")
    public String deleteExpense(@RequestParam Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }
}