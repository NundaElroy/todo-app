package com.nunda.controller;


import com.nunda.model.User;
import com.nunda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

import com.nunda.service.TodoService;
import com.nunda.model.TodoItem;

@Controller
public class TodoController{


    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService){
        this.todoService = todoService;
        this.userService = userService;
    }


    @GetMapping({"","/"})
    public String index(){
        return "guesthome";
    }

    @GetMapping("/features")
    public String features(){
        return "features";
    }


    @GetMapping("/home")
    public String welcome(Model model, Principal principal){
        //username of the logged in user
        String email = principal.getName();
        //getting the items sorted by there dates
        User user = userService.findByEmail(email);
        List<TodoItem> items = user.getTodoItems();
        TodoItem todoItem = new TodoItem();
        model.addAttribute("todoItem",todoItem);
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping("/archive")
    public String getArchive(Model model,Principal principal){
        //username of the logged in user
        String email = principal.getName();
        //getting the items sorted by there dates
        //getting the items sorted by there dates
        User user = userService.findByEmail(email);
        List<TodoItem> items = user.getTodoItems();
//      List<TodoItem> allItemsArrangedByDate = todoService.getAllItemsByDate();
        model.addAttribute("items",items);
        return "archive";
    }

    @PostMapping("/new-item")
    public String submitForm(@ModelAttribute TodoItem todoItem,Principal principal){
        String username = principal.getName();
         User user = userService.findByEmail(username);
         userService.addToTodoItemsList(user,todoItem);
         return "redirect:/home";
    }


    @PostMapping("/updateStatus")
    public String checkTodoItem(@RequestParam("itemId") Long itemId,@RequestParam("status") boolean status){
        TodoItem todoItem = todoService.getItemById(itemId);
        todoItem.setStatus(status);
        todoService.saveItem(todoItem);
        return "redirect:/home";
    }
    @PostMapping("/deleteItem")
    public String deleteTodoItem(@RequestParam("itemId") Long itemId){
        todoService.deleteItem(itemId);
        return  "redirect:/home";
    }

    @PostMapping("/deleteTodoItemFromArchive")
    public String deleteTodoItemFromArchive(@RequestParam("itemId") Long itemId){
        todoService.deleteItem(itemId);
        return  "redirect:/archive";
    }

}