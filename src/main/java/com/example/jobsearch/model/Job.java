package com.example.jobsearch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @Size(max = 50)
    private String title;


    @Size(max = 50)
    private String location;

}