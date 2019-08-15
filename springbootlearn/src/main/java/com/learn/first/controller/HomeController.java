package com.learn.first.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController{

    @Autowired
    HttpSession session;

    @RequestMapping("/")
    public String Home(){
        if(!"admin".equals(session.getAttribute("name"))){
            return "redirect:/login/";
        }
        return "home/index";
    }
}