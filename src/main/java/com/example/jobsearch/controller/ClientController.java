package com.example.jobsearch.controller;

import com.example.jobsearch.exception.RegistrationException;
import com.example.jobsearch.model.Client;
import com.example.jobsearch.service.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private HttpSession httpSession;


    @PostMapping
    public ResponseEntity<?> registerClient(@Validated @RequestBody Client client) {

        String apiKey = clientService.registerClient(client);
        httpSession.setAttribute("apiKey", apiKey);

        Map<Object, String> response = new HashMap<>();

        if(apiKey != null) {

            response.put("status", "success");
            response.put("message", "Client registered successfully");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else
        {
            throw new RegistrationException(HttpStatus.BAD_REQUEST, "Registration failed");
        }

    }

}