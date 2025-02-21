package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        model.addAttribute("name", "Judhistir");
        model.addAttribute("youtube", "jbcodes");
        model.addAttribute("google", "https://www.google.com");
        return "home";
    }

    //about page

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", false);
        return "about";
    }

    //service page

    @RequestMapping("/services")
    public String servicePage() {

        return "services";
    }
    
    //Contact page

    @RequestMapping("/contact")
    public String contactPage() {

        return "contact";
    }
    //Login page

    @RequestMapping("/login")
    public String loginPage() {

        return "login";
    }
    //Register page

    @RequestMapping("/register")
    public String RegisterPage() {

        return "register";
    }
    

}
