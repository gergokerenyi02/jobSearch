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

    @PostMapping
    public ResponseEntity<String> registerClient(@RequestBody Client client) {
        String apiKey = clientService.registerClient(client);
        return ResponseEntity.ok(apiKey);
    }

}