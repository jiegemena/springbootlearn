package com.learn.first.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController{

    @RequestMapping("/")
    public String Home(){
        return "First Spring boot app";
    }

}