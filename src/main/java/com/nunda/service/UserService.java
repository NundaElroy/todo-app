package com.nunda.service;

import com.nunda.model.TodoItem;
import com.nunda.model.User;
import com.nunda.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public void addToTodoItemsList(User user, TodoItem todoItem) {
        // Set the user in the TodoItem
        todoItem.setUser(user);

        // Add the TodoItem to the user's list
        List<TodoItem> todoItems = user.getTodoItems();
        todoItems.add(todoItem);

        // Save the user, which will also save the TodoItem
        userRepo.save(user);
    }


    public User getUserByUsername(String email){
          return userRepo.findByEmail(email);
    }


}
