package com.example.jobsearch.repository;

import com.example.jobsearch.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
