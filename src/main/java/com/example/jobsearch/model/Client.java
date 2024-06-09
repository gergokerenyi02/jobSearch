package com.example.jobsearch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;

import lombok.Setter;



@Entity
@Getter @Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @Size(max = 100)
    private String name;


    @Email
    @Column(unique = true)
    private String email;

    @Setter
    private String apiKey;


}