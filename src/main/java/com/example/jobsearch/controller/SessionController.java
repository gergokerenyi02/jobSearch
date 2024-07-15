package com.example.jobsearch.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/session") // Class level, Base URL path for all endpoints in this controller
public class SessionController {



    @GetMapping("/validate")
    public ResponseEntity<Map<Object, String>> validateSession(HttpSession httpSession) {
        String apiKey = (String) httpSession.getAttribute("apiKey");

        Map<Object, String> response = new HashMap<>();

        if (apiKey == null) {
            response.put("status", "error");
            response.put("message", "Invalid API Key/Session");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        } else
        {
            response.put("status", "success");
            response.put("message", "Valid API Key/Session. User is logged in.");
            return ResponseEntity.ok(response);
        }


    }

}
