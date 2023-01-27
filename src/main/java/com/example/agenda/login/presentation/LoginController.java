package com.example.agenda.login.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @GetMapping
    public String login(@RequestParam(required = false) boolean error, Model model) {
        if(error){
            model.addAttribute("errorMessage", "Oops! Looks like you've entered the wrong credentials. Please try again or sign up for a new account.");
        }
        return "login";
    }

}