package com.example.jobsearch.controller;

import com.example.jobsearch.model.Client;
import com.example.jobsearch.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controller réteg
// Megkapja a bejövő HTTP kéréseket és meghatározza milyen műveletet hajtson végre

@RestController // Returns JSON
@RequestMapping("/client")
public class ClientController {

    // ClientService Bean-t megkeresi a Spring és injektálja
    @Autowired
    private ClientService clientService;


    // @RequestBody a kapott JSON-t a index.html form post kérelemtől automatikusan deszerializálja és feltölti a Client object-tet a megfelelő mezőkkel
    // A ClientService registerClient metódusa meghívásra kerül ezzel a Client-tel.
    // Return: egy api kulcs

    @PostMapping
    public ResponseEntity<String> registerClient(@RequestBody Client client) {
        String apiKey = clientService.registerClient(client);
        return ResponseEntity.ok(apiKey);
    }

}