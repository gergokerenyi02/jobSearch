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


@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    /**
     * Creates a new job record.
     *
     * @param job The job to create.
     * @return The created job.
     * @throws ValidationException if the title or location is empty.
     */
    public Job createJob(Job job) {

        if(job.getTitle() == null || job.getTitle().isEmpty()) {
            throw new ValidationException(HttpStatus.BAD_REQUEST, "Title cannot be empty. Enter a valid title to create a new job record.");
        }
        if(job.getLocation() == null || job.getLocation().isEmpty()) {
            throw new ValidationException(HttpStatus.BAD_REQUEST, "Location cannot be empty. Enter a valid location to create a new job record.");
        }

        return jobRepository.save(job);
    }

    /**
     * Deletes a job by its ID.
     *
     * @param id The ID of the job to delete.
     * @throws JobNotFoundException if the job with the given ID is not found.
     */
    public void deleteJob(Long id) {
        Optional<Job> job = jobRepository.findById(id);

        if (job.isPresent()) {
            jobRepository.delete(job.get());
        } else {
            throw new JobNotFoundException(HttpStatus.NOT_FOUND, "Job with the ID " + id + " is not found and cannot be deleted.");
        }
    }

    /**
     * Modifies an existing job.
     *
     * @param job The job to modify.
     * @throws JobNotFoundException if the job with the given ID is not found.
     */
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

    /**
     * Searches for jobs by title or location keyword.
     *
     * @param keyword_title    The keyword to search in job titles.
     * @param keyword_location The keyword to search in job locations.
     * @return A list of jobs that match the search criteria.
     */
    public List<Job> searchJobsByKeyword(String keyword_title, String keyword_location) {
        return jobRepository.findByTitleContainingOrLocationContaining(keyword_title, keyword_location);
    }

    /**
     * Retrieves a job by its ID.
     *
     * @param id The ID of the job to retrieve.
     * @return The job with the given ID, or null if not found.
     */
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

}