package com.learn.first.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login")
public class LoginController{

    @Autowired
    HttpSession session;

    @RequestMapping("")
    public String index(){
        return "login/index";
    }

    @RequestMapping("/in")
    public RedirectView in(@RequestParam String lname,@RequestParam String pwd){
        if("admin".equals(lname) && "admin".equals(pwd)){
            RedirectView redirectTarget = new RedirectView();
            redirectTarget.setContextRelative(true);
            redirectTarget.setUrl("/");
            session.setAttribute("name", "admin");
            
            return redirectTarget;
        }

        RedirectView redirectTarget = new RedirectView();
        redirectTarget.setContextRelative(true);
        redirectTarget.setUrl("/login/");
        return redirectTarget;
    }

    @RequestMapping("/out")
    public String out(){
        // session.setAttribute("name", null);
        session.removeAttribute("name");
        return "redirect:/login/";
    }
}