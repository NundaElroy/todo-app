package com.nunda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("items", items);
        return "index";
    }

}