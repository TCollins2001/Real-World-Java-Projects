package com.teonvioncollins.BankSystem.controllers;

import com.teonvioncollins.BankSystem.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankController {

    @Autowired
    BankService bankService;

    @PostMapping("/deposit-funds")
    public String depositFunds(@RequestParam String accountType, @RequestParam double amount) {
        bankService.depositFunds(accountType, amount);
        return "redirect:/accounts";
    }

    @PostMapping("/withdraw-funds")
    public String withdrawFunds(@RequestParam String accountType, @RequestParam double amount) {
        bankService.withdrawFunds(accountType, amount);
        return "redirect:/accounts";
    }
}
