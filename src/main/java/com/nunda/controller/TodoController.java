package com.nunda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nunda.service.TodoService;

@Controller
public class TodoController{

    @Autowired
    private TodoService todoService;


    @GetMapping
    public String welcome(){
        return "index";
    }

}