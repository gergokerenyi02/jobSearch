package com.example.jobsearch.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        String message = (String) session.getAttribute("message");
        model.addAttribute("welcome_message", "Welcome to Job Search Application!");
        if (message != null) {

            model.addAttribute("message", message);
            session.removeAttribute("message");
        }

        return "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}