package com.example.jobsearch.controller;

import com.example.jobsearch.model.Client;
import com.example.jobsearch.service.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;




// Controller réteg
// Megkapja a bejövő HTTP kéréseket és meghatározza milyen műveletet hajtson végre

@RestController // Returns JSON
//@Controller // Returns HTML
@RequestMapping("/client")
public class ClientController {

    // ClientService Bean-t megkeresi a Spring és injektálja
    @Autowired
    private ClientService clientService;
    @Autowired
    private HttpSession httpSession;


    // @RequestBody a kapott JSON-t a index.html form post kérelemtől automatikusan deszerializálja és feltölti a Client object-tet a megfelelő mezőkkel
    // A ClientService registerClient metódusa meghívásra kerül ezzel a Client-tel.
    // Return: egy api kulcs

    // Error: @RequestBody miatt
    // Form küldési típusa: (Content-Type) application/x-www-form-urlencoded
    // A @RequestBody JSON-t vár

    // Megoldás
    // Használjunk @ModelAttribute annotációt

    @PostMapping
    public ResponseEntity<?> registerClient(@Validated @RequestBody Client client) {
        String apiKey = clientService.registerClient(client);

        httpSession.setAttribute("apiKey", apiKey);

        return new ResponseEntity<>("Client registered successfully." , HttpStatus.OK);

    }

}