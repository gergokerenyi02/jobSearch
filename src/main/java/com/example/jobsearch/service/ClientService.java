package com.example.jobsearch.service;

import java.util.Optional;
import java.util.UUID;
import com.example.jobsearch.model.Client;
import com.example.jobsearch.model.Job;
import com.example.jobsearch.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



// Szolgáltatás

// Itt:
// Üzleti logika:
// Kliens regisztrációja: VALIDÁCIÓ, API kulcs generálás, Adatbázisba mentés


// Összegzés
// Az üzleti logika az alkalmazás működésének a központi része, felelős az adatok kezeléséért az üzleti szabályok és folyamatok alapján
// @Service annotációval ellátott osztály, így elkülönül a Controller és Repository rétegtől


@Service // Szolgáltatás réteg jelzése
public class ClientService {

    // Autowired annotáció DI (Dependency Injection)
    // Megkeresi a Spring a Bean-t és injektálja
    @Autowired
    private ClientRepository clientRepository;

    public String registerClient(Client client) {
        client.setApiKey(UUID.randomUUID().toString());
        clientRepository.save(client);
        return client.getApiKey();
    }

    public boolean isValidApiKey(String apiKey) {
        Optional<Client> client = clientRepository.findByApiKey(apiKey);
        return client.isPresent();
    }


}