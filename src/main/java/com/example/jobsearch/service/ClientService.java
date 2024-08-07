package com.example.jobsearch.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import com.example.jobsearch.exception.ValidationException;
import com.example.jobsearch.model.Client;
import com.example.jobsearch.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        if(client.getEmail() == null || client.getEmail().isEmpty()){
            throw new ValidationException(HttpStatus.BAD_REQUEST,"The email you entered can not be null or empty!");

        }
        if(clientRepository.findByEmail(client.getEmail()).isPresent()){
            throw new ValidationException(HttpStatus.BAD_REQUEST, "The user with the entered email address is already registered!");
        }
        if(client.getName() == null || client.getName().isEmpty()){
            throw new ValidationException(HttpStatus.BAD_REQUEST, "The name you entered can not be null or empty!");
        }


        client.setApiKey(UUID.randomUUID().toString());
        clientRepository.save(client);
        return client.getApiKey();
    }

    public boolean isValidApiKey(String apiKey) {
        Optional<Client> client = clientRepository.findByApiKey(apiKey);
        return client.isPresent();
    }

    public String findUserEmailByApiKey(String apiKey)
    {
        Optional<Client> optionalClient = clientRepository.findByApiKey(apiKey);
        if(optionalClient.isPresent()){
            return optionalClient.get().getEmail();
        } else
        {
            throw new NoSuchElementException("No client found with the provided API key!");
        }

    }
    public String findUserNameByApiKey(String apiKey) {
        Optional<Client> clientOptional = clientRepository.findByApiKey(apiKey);
        if (clientOptional.isPresent()) {
            return clientOptional.get().getName();
        } else {
            throw new NoSuchElementException("No client found with the provided API key!");
        }
    }

}