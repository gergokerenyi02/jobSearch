package com.example.jobsearch.model;

import javax.persistence.*;
//import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job")
@Getter @Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    //@Size(max = 50)
    private String title;
    @Column(name="location")
    //@Size(max = 50)
    private String location;
}
