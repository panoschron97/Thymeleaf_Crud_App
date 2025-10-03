package com.thymeleaf.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage()
    {

        return "fancy-login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied()
    {

        return "access-denied";
    }

    @PostMapping("/access-denied")
    public String showAccessDeniedForPost()
    {

        return "access-denied";
    }
}