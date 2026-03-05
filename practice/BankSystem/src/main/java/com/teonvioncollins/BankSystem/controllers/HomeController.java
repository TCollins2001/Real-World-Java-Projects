package com.teonvioncollins.BankSystem.controllers;

import com.teonvioncollins.BankSystem.models.BankModel;
import com.teonvioncollins.BankSystem.repos.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private BankRepo bankRepo;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/accounts")
    public String accounts(Model model) {

        BankModel savings = bankRepo.findByAccountType("SAVINGS");
        BankModel checking = bankRepo.findByAccountType("CHECKING");

        model.addAttribute("savings", savings);
        model.addAttribute("checking", checking);
        return "accounts";
    }

}
