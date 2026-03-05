package com.teonvioncollins.ExpenseTracker.controllers;
import com.teonvioncollins.ExpenseTracker.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/expenses")
    public String expenses(Model model) {
        model.addAttribute("expenses", expenseService.getAllExpenses());
        model.addAttribute("total", expenseService.getTotalExpenses());
        return "expenses";
    }
}
