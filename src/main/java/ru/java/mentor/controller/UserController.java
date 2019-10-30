package ru.java.mentor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.java.mentor.model.User;
import ru.java.mentor.service.UserService;
import java.security.Principal;

@Controller
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public String hello(Principal principal, Model model){
        User byLogin = service.getUserByLogin(principal.getName());
        model.addAttribute("byLogin", byLogin);
        return "userPage";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "onlyAdmin";
    }
}
