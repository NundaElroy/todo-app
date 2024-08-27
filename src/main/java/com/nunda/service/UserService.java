package com.nunda.service;

import com.nunda.model.User;
import com.nunda.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
     private final UserRepo userRepo;
     private  final EmailService emailService;

     public UserService(UserRepo userRepo, EmailService emailService){
         this.userRepo = userRepo;
         this.emailService = emailService;
     }

        public User findByEmail(String email){
            return userRepo.findByEmail(email);
        }

        public void save(User user){
            emailService.sendEmail(
                    user.getEmail(),
                    "Registration Confirmation",
                    "Thank you for registering, " + user.getUsername() + "!"
            );

            userRepo.save(user);
        }

}
