package com.example.jobsearch.repository;


import com.example.jobsearch.model.Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Adat-hozzáférési komponens (DAO)
// JpaRepository<Client, Long> -> INTERFACE (Spring Data JPA)
// biztosítja az alapvető adat-hozzáférési műveletekhez szükséges metódusokat (save, findAll, findById, deleteById)

@Component @Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByApiKey(String apiKey);
    Optional<Client> findByEmail(String email);
}
