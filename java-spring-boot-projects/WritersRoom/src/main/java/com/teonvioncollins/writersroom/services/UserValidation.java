package com.teonvioncollins.writersroom.services;

import com.teonvioncollins.writersroom.models.User;
import com.teonvioncollins.writersroom.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class UserValidation {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserValidation.class);

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z]).{6,}$");

    public Map<String, String> validateRegistration(User u) {
        Map<String, String> errors = new HashMap<>();

        if (u.getUsername() != null && u.getUsername().length() < 3) {
            errors.put("username", "Username must be at least 3 characters long.");
        }

        if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
            errors.put("email", "Email is required.");
        }

        if (u.getPassword() == null || u.getPassword().trim().isEmpty()) {
            errors.put("password", "Password is required.");
        } else if (!EMAIL_PATTERN.matcher(u.getEmail()).matches()) {
            errors.put("email", "Invalid email format.");
        } else if (userExists(u.getUsername())) {
            errors.put("username", "Username already registered.");
        }

        if (u.getPassword() == null || u.getPassword().trim().isEmpty()) {
            errors.put("password", "Password is required.");
        } else if (u.getConfirmPassword() == null || u.getConfirmPassword().trim().isEmpty()) {
            errors.put("confirmPassword", "Please confirm your password.");
        } else if (!u.getPassword().equals(u.getConfirmPassword())) {
            errors.put("confirmPassword", "Passwords do not match.");
        }
        logger.info("Password: {}, Confirm: {}", u.getPassword(), u.getConfirmPassword());

        if (u.getPassword() != null && !PASSWORD_PATTERN.matcher(u.getPassword()).matches()) {
            errors.put("password", "Password must be at least 6 characters and contain 1 uppercase letter.");
        }

        if (errors.isEmpty()) {
            logger.info("User validation passed for email: {}", u.getEmail());
        } else {
            logger.warn("Validation failed for user {}: {}", u.getEmail(), errors);
        }

        return errors;
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            if (!user.getPassword().startsWith("$2a$")) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(user.getPassword()));
            }
            return userRepository.save(user);
        }

        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found for update"));

        if (user.getUsername() != null) existing.setUsername(user.getUsername());
        if (user.getEmail() != null) existing.setEmail(user.getEmail());

        if (user.getPassword() != null && !user.getPassword().isBlank()
                && !existing.getPassword().equals(user.getPassword())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            existing.setPassword(encoder.encode(user.getPassword()));
        }

        return userRepository.save(existing);
    }


    public Map<String, String> validateLogin(User inputUser) {
        Map<String, String> errors = new HashMap<>();

        if (inputUser.getUsername() == null || inputUser.getUsername().trim().isEmpty()) {
            errors.put("username", "Username is required.");
            return errors;
        }

        if (inputUser.getPassword() == null || inputUser.getPassword().trim().isEmpty()) {
            errors.put("password", "Password is required.");
            return errors;
        }

        var existingUser = userRepository.findByUsername(inputUser.getUsername());

        if (existingUser.isEmpty()) {
            errors.put("username", "No account found with that username.");
            return errors;
        }

        User dbUser = existingUser.get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(inputUser.getPassword(), dbUser.getPassword())) {
            errors.put("password", "Incorrect password.");
        }

        logger.info("Login successful for username: {}", inputUser.getUsername());
        return errors;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }
}