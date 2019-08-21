package com.learn.first.controller;

import javax.servlet.http.HttpSession;

import com.learn.first.services.userservices.UserService;
import com.learn.first.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login2")
public class Login2Controller{

    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @RequestMapping("")
    public String index(){
        //TODO TEST
        if(userService.getUserByName("admin") == null){
            User user = new User();
            user.setUserName("admin");
            user.setPassWord("admin");
            user.setPhoneNum("110900");
            userService.Add(user);
        }
        return "login/index";
    }

    @RequestMapping("/in")
    public String in(@RequestParam String lname,@RequestParam String pwd){
        User user = userService.getUserByName(lname);
        if(user != null && user.checkPassWorl(pwd)){
            session.setAttribute("name", "admin");
            return "redirect:/";
        }
        return "redirect:/login/";
    }

    @RequestMapping("/out")
    public String out(){
        // session.setAttribute("name", null);
        session.removeAttribute("name");
        return "redirect:/login/";
    }
}