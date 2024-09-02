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


@Service
public class ClientService {


    @Autowired
    private ClientRepository clientRepository;


    public String registerClient(Client client) {

        if(client.getName() == null || client.getName().isEmpty()){
            throw new ValidationException(HttpStatus.BAD_REQUEST, "The name you entered can not be null or empty!");
        }

        if(client.getEmail() == null || client.getEmail().isEmpty()){
            throw new ValidationException(HttpStatus.BAD_REQUEST,"The email you entered can not be null or empty!");

        }

        if(clientRepository.findByEmail(client.getEmail()).isPresent()){
            throw new ValidationException(HttpStatus.BAD_REQUEST, "The user with the entered email address is already registered!");
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