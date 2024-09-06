package com.nunda.controller;

import com.nunda.model.User;
import com.nunda.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final UserService userService;

    public GlobalControllerAdvice(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addUserToModel(Model model, Principal principal) {
        if (principal != null) {
            String userEmail = principal.getName();
            User user = userService.getUserByUsername(userEmail);

            // Use default profile picture if the user does not have one
            String profilePicture = user.getFilename();
            if (profilePicture == null || profilePicture.isEmpty()) {
                profilePicture = "default_profile.jpeg"; // Default picture filename
            }

            user.setFilename(profilePicture); // Set filename to default if not present
            model.addAttribute("user", user);
        }
    }
}
