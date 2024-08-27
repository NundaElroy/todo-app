package com.nunda.controller;

import com.nunda.model.User;
import com.nunda.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.nunda.validators.MyRegistrationValidator;


@Controller
public class UserController {
    private final UserService userService;
    private  final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }



    @GetMapping("/login")
    public String login(Model model){
        Boolean successfulRegistration = false;
        model.addAttribute("success", successfulRegistration);
      return "login" ;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("applicantDetails", new MyRegistrationValidator());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser( @ModelAttribute("applicantDetails") @Valid MyRegistrationValidator applicantDetails, BindingResult result,Model model){
        if(result.hasErrors()){
            return "register";
        }
        if (!applicantDetails.getPassword().equals(applicantDetails.getConfirmPassword())){
            result.rejectValue("password", "error.applicantDetails", "Password does not match");
            return "register";
        }
        User user = userService.findByEmail(applicantDetails.getEmail());
        if(user != null){
            result.rejectValue("email", "error.applicantDetails", "Email is already in use");
            return "register";
        }
        User newUser = new User();
        newUser.setUsername(applicantDetails.getUsername());
        newUser.setEmail(applicantDetails.getEmail());
        newUser.setPassword(passwordEncoder.encode(applicantDetails.getPassword()));

        userService.save(newUser);

        model.addAttribute("success", true);
       return "redirect:/login";

    }

}
