package com.example.jobsearch.model;

import javax.persistence.*;
//import jakarta.validation.constraints.*;
import lombok.Getter;

import lombok.Setter;



@Entity
@Table(name = "client")
@Getter @Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id")
    private Long id;



    //@Size(max = 100)
    @Column(name="name")
    private String name;


    //@Email(regexp = ".+[@].+[\\.].+")

    @Column(unique = true, name = "email")
    private String email;

    @Setter
    @Column(name="apiKey")
    private String apiKey;


}