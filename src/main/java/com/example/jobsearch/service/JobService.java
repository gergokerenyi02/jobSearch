package com.example.jobsearch.service;

import com.example.jobsearch.exception.JobNotFoundException;
import com.example.jobsearch.exception.ValidationException;
import com.example.jobsearch.model.Job;
import java.util.List;
import java.util.Optional;

import com.example.jobsearch.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

// Állás létrehozása: VALIDÁCIÓ, API kulcs ellenőrzése, Adatbázisba mentés
// Állás keresés: API kulcs ellenőrzés, keresés végrehajtása, eredmények visszaadása


@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {

        if(job.getTitle() == null || job.getTitle().isEmpty()) {
            throw new ValidationException(HttpStatus.BAD_REQUEST, "Title cannot be empty. Enter a valid title to create a new job record.");
        }
        if(job.getLocation() == null || job.getLocation().isEmpty()) {
            throw new ValidationException(HttpStatus.BAD_REQUEST, "Location cannot be empty. Enter a valid location to create a new job record.");
        }

        return jobRepository.save(job);
    }


    public void deleteJob(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        job.ifPresent(value -> jobRepository.delete(value));

    }

    public void modifyJob(Job job) {
        Optional<Job> jobOptional = jobRepository.findById(job.getId());

        if(jobOptional.isPresent()) {

            Job existingJob = jobOptional.get();
            existingJob.setTitle(job.getTitle());
            existingJob.setLocation(job.getLocation());

            // Save the updated job back to the repository
            jobRepository.save(existingJob);

        } else
        {
            throw new JobNotFoundException(HttpStatus.NOT_FOUND, "Job with the ID " + job.getId() + " is not found.");
        }
    }

    public List<Job> searchJobsByKeyword(String keyword_title, String keyword_location) {
        return jobRepository.findByTitleContainingOrLocationContaining(keyword_title, keyword_location);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

}
