package com.example.jobsearch.repository;


import com.example.jobsearch.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Adat-hozzáférési komponens (DAO)
// JpaRepository<Client, Long> -> INTERFACE (Spring Data JPA)
// biztosítja az alapvető adat-hozzáférési műveletekhez szükséges metódusokat (save, findAll, findById, deleteById)

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
