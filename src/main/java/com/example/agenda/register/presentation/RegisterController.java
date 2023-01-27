package com.example.agenda.register.presentation;

import com.example.agenda.login.data.UserService;
import com.example.agenda.register.domain.RegisterPayload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getRegisterPage(Model model) {
        model.addAttribute("registerPayload", new RegisterPayload());
        return "register";
    }

    @PostMapping
    public String register(@Valid RegisterPayload payload, BindingResult result, Model model, HttpServletRequest request) {
        if(result.hasErrors()) {
            if(!Objects.equals(payload.getPassword(), payload.getPasswordConfirmation())){
                model.addAttribute("errorMessage", "Passwords didn't match");
                return "register";
            }
            return "register";
        }

        userService.registerUser(payload.getEmail(), payload.getPassword(), payload.getFirstName(), payload.getLastName());
            return "redirect:/login";
    }
}