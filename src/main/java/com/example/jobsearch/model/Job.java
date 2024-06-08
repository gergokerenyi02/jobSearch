package com.example.jobsearch.model;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Job {
    private Long id;
    private String title;
    private String location;
    private String description;


}