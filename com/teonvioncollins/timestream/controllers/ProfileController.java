package com.teonvioncollins.timestream.controllers;

import com.teonvioncollins.timestream.models.User;
import com.teonvioncollins.timestream.repositories.UserRepo;
import com.teonvioncollins.timestream.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PatchMapping("/update")
    public void updateProfileField(
            @RequestBody Map<String, String> payload,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return;

        String field = payload.get("field");
        String value = payload.get("value");

        userService.updateUserField(user, field, value);

        session.setAttribute("loggedInUser", user);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody Map<String, String> payload,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return ResponseEntity.status(401).build();

        String currentPassword = payload.get("currentPassword");
        String newPassword = payload.get("newPassword");

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return ResponseEntity.status(400).body("Wrong password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/update-theme")
    public ResponseEntity<?> updateTheme(
            @RequestBody Map<String, String> body,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return ResponseEntity.status(401).build();

        String theme = body.get("theme");
        user.setRegenTheme(theme);
        userRepo.save(user);

        session.setAttribute("loggedInUser", user);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(HttpSession session)
     {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        userService.deleteUserById(user.getId());
        session.invalidate();

         return ResponseEntity.ok().build();
    }
}
