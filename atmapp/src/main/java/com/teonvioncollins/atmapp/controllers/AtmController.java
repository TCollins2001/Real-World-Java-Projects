package com.teonvioncollins.atmapp.controllers;

import com.teonvioncollins.atmapp.services.AtmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@Controller
public class AtmController {

    @GetMapping("/homepage")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        return "menu";
    }

    @GetMapping("/view")
    public String view(Model model) {
        DecimalFormat df = new DecimalFormat("0.00");
        model.addAttribute("userBalance", df.format(AtmService.getBalance()));
        return "view";
    }

    @GetMapping("/deposit")
    public String deposit(Model model) {
        return "deposit";
    }

    @PostMapping("/deposit")
    public String makeDeposit(@RequestParam double amount) {
        AtmService.makeDeposit(amount);
        return "redirect:/view";
    }

    @GetMapping("/withdraw")
    public String withdraw(Model model) {
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String makeWithdraw(@RequestParam double amountW) {
        AtmService.makeWithdraw(amountW);
        return "redirect:/view";
    }
}


