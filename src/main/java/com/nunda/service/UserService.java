package com.nunda.service;

import com.nunda.model.User;
import com.nunda.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
     private final UserRepo userRepo;

     public UserService(UserRepo userRepo){
         this.userRepo = userRepo;
     }

        public User findByEmail(String email){
            return userRepo.findByEmail(email);
        }

        public void save(User user){
            userRepo.save(user);
        }

}
