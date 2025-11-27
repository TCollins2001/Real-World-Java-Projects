package com.teonvioncollins.timestream.controllers;

import com.teonvioncollins.timestream.models.User;
import com.teonvioncollins.timestream.services.UserValidation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
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
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userValidation.saveUser(user);

        session.setAttribute("loggedInUser", savedUser);
        return "redirect:/all-chats";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session) {
        Map<String, String> errors = userValidation.validateLogin(user);

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "sign-in";
        }

        User dbUser = userValidation.findByUsername(user.getUsername());
        session.setAttribute("loggedInUser", dbUser);

        return "redirect:/all-chats";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/index";
}

    @PostMapping("/upload-profile-pic")
    public String uploadProfilePicture(
            @RequestParam("profile_pic") MultipartFile profile_pic,
            HttpSession session) throws IOException {
        User sessionUser = (User) session.getAttribute("loggedInUser");
        if (sessionUser == null) return "redirect:/sign-in";

        if (!profile_pic.isEmpty()) {
            Path uploadPath = Paths.get(System.getProperty("user.home"), "timestream_uploads");
            Files.createDirectories(uploadPath);

            String fileName = System.currentTimeMillis() + "_" + profile_pic.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(profile_pic.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            userValidation.updateProfilePic(sessionUser.getUsername(), "/uploads/" + fileName);
            sessionUser.setProfilePic("/uploads/" + fileName);
            session.setAttribute("loggedInUser", sessionUser);
        }

        return "redirect:/profile";
    }

    @PostMapping("/upload-cover-pic")
    public String uploadCoverPicture(
            @RequestParam("cover_pic") MultipartFile cover_pic,
            HttpSession session) throws IOException {
        User sessionUser = (User) session.getAttribute("loggedInUser");
        if (sessionUser == null) return "redirect:/sign-in";

        if (!cover_pic.isEmpty()) {
            Path uploadPath = Paths.get(System.getProperty("user.home"), "timestream_uploads");
            Files.createDirectories(uploadPath);

            String fileName = System.currentTimeMillis() + "_" + cover_pic.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(cover_pic.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            userValidation.updateCoverPic(sessionUser.getUsername(), "/uploads/" + fileName);
            sessionUser.setCoverPic("/uploads/" + fileName);
            session.setAttribute("loggedInUser", sessionUser);
        }

        return "redirect:/profile";
    }

    @PostMapping("/reset-profile-pic")
    public String resetProfilePic(HttpSession session) {
        User sessionUser = (User) session.getAttribute("loggedInUser");
        if (sessionUser == null) return "redirect:/sign-in";

        String defaultPic = "/images/no-profile-pic.png";

        userValidation.updateProfilePic(sessionUser.getUsername(), defaultPic);
        sessionUser.setProfilePic(defaultPic);

        session.setAttribute("loggedInUser", sessionUser);

        return "redirect:/profile";
    }

    @PostMapping("/reset-cover-pic")
    public String resetCoverPic(HttpSession session) {
        User sessionUser = (User) session.getAttribute("loggedInUser");
        if (sessionUser == null) return "redirect:/sign-in";

        String defaultCover = "/images/doctor-who-bg.jpg";

        userValidation.updateCoverPic(sessionUser.getUsername(), defaultCover);
        sessionUser.setCoverPic(defaultCover);

        session.setAttribute("loggedInUser", sessionUser);

        return "redirect:/profile";
    }
}
