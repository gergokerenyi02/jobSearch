package com.example.jobsearch.service;

import com.example.jobsearch.model.Job;
import java.util.List;
import com.example.jobsearch.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Állás létrehozása: VALIDÁCIÓ, API kulcs ellenőrzése, Adatbázisba mentés
// Állás keresés: API kulcs ellenőrzés, keresés végrehajtása, eredmények visszaadása


@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> searchJobsByKeyword(String keyword_title, String keyword_location) {
        return jobRepository.findByTitleContainingOrLocationContaining(keyword_title, keyword_location);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

}
