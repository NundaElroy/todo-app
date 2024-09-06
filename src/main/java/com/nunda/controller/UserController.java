package com.nunda.controller;

import com.nunda.model.User;
import com.nunda.service.EmailService;
import com.nunda.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.nunda.validators.MyRegistrationValidator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.UUID;


@Controller
public class UserController {
    private final UserService userService;
    private  final PasswordEncoder passwordEncoder;
    private  final EmailService emailService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder,EmailService emailService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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
        emailService.sendEmail(
                newUser.getEmail(),
                "Registration Confirmation",
                "Thank you for registering, " + user.getUsername() + "!"
        );

        model.addAttribute("success", true);
       return "redirect:/login";

    }
    @GetMapping("/userprofile")
    public  String getUserProfile(){
//        User user = userService.getUserByUsername(principal.getName());
//        model.addAttribute("user", user);
        return "userprofile";
    }
    @PostMapping("/uploadProfilePicture")
    public String uploadProfilePicture(
            @RequestParam("image")MultipartFile file ,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {

        try{
            String filename = file.getOriginalFilename();
            String userEmail= principal.getName();
            User user = userService.getUserByUsername(userEmail);

            // Generate a unique filename if necessary
            String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
            //save  it in the database
            user.setFilename(uniqueFilename);
            userService.save(user);

            //saving the file in our file system
            Path path = Paths.get("src/main/resources/static/images/" + uniqueFilename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            redirectAttributes.addFlashAttribute("message", "Image uploaded successfully!");
            return "redirect:/userprofile";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Image upload failed!");
            return "redirect:/userprofile";
        }
    }

}
