package com.example.jobsearch.controller;



import com.example.jobsearch.service.ClientService;
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


    @Autowired
    private ClientService clientService;
    @Autowired
    private HttpSession session;

    @GetMapping("/validate")
    public ResponseEntity<Map<Object, String>> validateSession() {
        String apiKey = (String) session.getAttribute("apiKey");

        Map<Object, String> response = new HashMap<>();

        if (apiKey == null || !clientService.isValidApiKey(apiKey)) {
            response.put("status", "error");
            response.put("message", "Invalid API Key/Session");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        } else if(clientService.isValidApiKey(apiKey))
        {
            response.put("status", "success");
            response.put("message", "Valid API Key/Session. User is logged in.");

            session.setAttribute("sessionName", clientService.findUserNameByApiKey(apiKey));
            session.setAttribute("sessionEmail", clientService.findUserEmailByApiKey(apiKey));

            response.put("user", clientService.findUserEmailByApiKey(apiKey));
            return ResponseEntity.ok(response);
        } else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    }

}
