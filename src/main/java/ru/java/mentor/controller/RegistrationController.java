package ru.java.mentor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.java.mentor.model.User;
import ru.java.mentor.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService service;

    @Autowired
    private Environment environment;

    @GetMapping("/reg")
    public String reg(@ModelAttribute("message") String message) {
        return "registration";
    }

    @PostMapping("/reg/user")
    public String regUser(@ModelAttribute("user") User user, Model model) {
        System.out.println("start");
        if (service.addUser(user)) {
            return "redirect:/";
        } else {
            model.addAttribute("message", environment.getRequiredProperty("invalidData"));
        }
        return "redirect:/reg";
    }
}
