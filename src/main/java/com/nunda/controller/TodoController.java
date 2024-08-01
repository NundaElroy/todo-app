package com.nunda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

import com.nunda.service.TodoService;
import com.nunda.model.TodoItem;

@Controller
public class TodoController{

    @Autowired
    private TodoService todoService;


    @GetMapping
    public String welcome(Model model){
        //getting the items sorted by there dates 
        List<TodoItem> items = todoService.getAllItems();
        TodoItem todoItem = new TodoItem();
        model.addAttribute("todoItem",todoItem);
        model.addAttribute("items", items);
        return "index";
    }

    @PostMapping("/new-item")
    public String submitForm(@ModelAttribute TodoItem todoItem){
         todoService.saveItem(todoItem);
         return "redirect:";

    }
    @PostMapping("/updateStatus")
    public String checkTodoItem(@RequestParam("itemId") Long itemId,@RequestParam("status") boolean status){
        TodoItem todoItem = todoService.getItemById(itemId);
        todoItem.setStatus(status);
        todoService.saveItem(todoItem);
        return "redirect:";
    }

}