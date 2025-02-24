package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

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
    public String RegisterPage(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    //processing registration
    @RequestMapping(value = "/registerForm", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        System.out.println("Processing Registraion");
        //fetch form data
        System.out.println(userForm);
        //validate formdata

        if (rBindingResult.hasErrors()) {
            return "register";
        }


        //save to db

        // Userform ----->  User  
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .phoneNumber(userForm.getPhoneNumber())
        // .about(userForm.getAbout())
        // .profilePic("https://img.freepik.com/free-vector/isolated-young-handsome-man-different-poses-white-background-illustration_632498-855.jpg?ga=GA1.1.2087950617.1738908788&semt=ais_hybrid")
        //         .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout());
        user.setProfilePic("https://img.freepik.com/free-vector/isolated-young-handsome-man-different-poses-white-background-illustration_632498-855.jpg?ga=GA1.1.2087950617.1738908788&semt=ais_hybrid");
        
        User savedUser = userService.saveUser(user);

        System.out.println("USer saved..");

        //message

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);
        //return to login page

        return "redirect:/register";
    }
    

}
