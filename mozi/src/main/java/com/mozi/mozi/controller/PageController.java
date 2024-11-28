package com.mozi.mozi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Ez a login.html sablon neve
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Ez a register.html sablon neve
    }
}

