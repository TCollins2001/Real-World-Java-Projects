package com.teonvioncollins.writersroom.controllers;

import com.teonvioncollins.writersroom.models.User;
import com.teonvioncollins.writersroom.services.UserValidation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserValidation userValidation;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model, HttpSession session) {
        Map<String, String> errors = userValidation.validateRegistration(user);


        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "sign-up";
        }
        User savedUser = userValidation.saveUser(user);

        session.setAttribute("loggedInUser", savedUser);
        return "redirect:/notes";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User inputUser,
                            HttpSession session,
                            Model model) {

        Map<String, String> errors = userValidation.validateLogin(inputUser);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "login";
        }

        User dbUser = userValidation.findByUsername(inputUser.getUsername());

        session.setAttribute("user", dbUser);

        return "redirect:/notes";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}

