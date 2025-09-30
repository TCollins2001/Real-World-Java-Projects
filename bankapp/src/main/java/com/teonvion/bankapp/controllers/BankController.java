package com.teonvion.bankapp.controllers;

import com.teonvion.bankapp.services.BankLogic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("balance", df.format(BankLogic.getBalance()));
        return "view";
    }
}
