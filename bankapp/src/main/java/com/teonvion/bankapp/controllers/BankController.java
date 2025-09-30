package com.teonvion.bankapp.controllers;

import com.teonvion.bankapp.services.BankLogic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@Controller
public class BankController {
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/view")
    public String view(Model model) {
        DecimalFormat df = new DecimalFormat("0.00");
        model.addAttribute("userBalance", df.format(BankLogic.getBalance()));
        return "view";
    }

    @GetMapping("/deposit")
    public String deposit(Model model) {
        DecimalFormat df = new DecimalFormat("0.00");
        double balance = BankLogic.getBalance();
        model.addAttribute("userDeposit", df.format(balance));
        return "deposit";
    }

    @PostMapping("/deposit")
    public String makeDeposit(@RequestParam double amount) {
        BankLogic.makeDeposit(amount);
        return "redirect:/view";
    }

    @GetMapping("/withdraw")
    public String withdraw(Model model) {
        return "withdraw";
    }
}
