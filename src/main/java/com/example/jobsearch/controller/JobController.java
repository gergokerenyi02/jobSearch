package com.example.jobsearch.controller;

import com.example.jobsearch.exception.ApiException;
import com.example.jobsearch.model.Job;

import com.example.jobsearch.service.ClientService;
import com.example.jobsearch.service.JobService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/position")
public class JobController {


    @Autowired
    private JobService jobService;

    @Autowired
    private ClientService clientService;
    @Autowired
    private HttpSession httpSession;

    /**
     * Creates a new job.
     * This method handles the creation of a new job by validating the session,
     * saving the job through the service, and returning a response indicating success or failure.
     *
     * @param job The job data received from the request body.
     * @return ResponseEntity containing the status, message, and job URL if successful,
     *         or an error message if the job creation fails.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createJob(@RequestBody Job job) {

        checkSession();

        Map<Object, String> response = new HashMap<>();

        try {
            Job savedJob = jobService.createJob(job);
            String jobUrl = "/position/" + savedJob.getId();

            response.put("status", "success");
            response.put("message", "Job created successfully!");
            response.put("jobUrl", jobUrl);

            return ResponseEntity.ok(response);
        } catch(Exception e)
        {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


    }


    /**
     * Deletes a job by its ID.
     * This method handles the deletion of a job. It finds the job by ID and deletes it
     * using the job service.
     *
     * @param id The ID of the job to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable String id) {

        checkSession();

        Map<Object, String> response = new HashMap<>();

        try {
            jobService.deleteJob(Long.valueOf(id));
            response.put("status", "success");
            response.put("message", "Job deleted successfully!");

            return ResponseEntity.ok(response);
        } catch(Exception e)
        {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }


    /**
     * Modifies an existing job.
     * This method updates the details of an existing job. It validates the session,
     * modifies the job using the service, and returns a response indicating success or failure.
     *
     * @param job The job data received from the request body.
     * @return ResponseEntity containing the status and message indicating success or failure.
     */
    @PutMapping("/modify")
    public ResponseEntity<?> updateJob(@RequestBody Job job)
    {
        checkSession();

        Map<Object, String> response = new HashMap<>();

        try
        {
            jobService.modifyJob(job);
            response.put("status", "success");
            response.put("message", "Job modified successfully.");

            return ResponseEntity.ok(response);
        } catch(Exception e)
        {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


    }


    /**
     * Searches for jobs based on keywords.
     * This method searches for jobs by title and location keywords, validates the session,
     * and returns a list of matching jobs.
     *
     * @param keywordTitle The keyword to search for in job titles.
     * @param keywordLocation The keyword to search for in job locations.
     * @return ResponseEntity containing a list of matching jobs with their details.
     */
    @GetMapping("/browse")
    public ResponseEntity<?> searchJobs(@RequestParam String keywordTitle, @RequestParam String keywordLocation) {

        checkSession();

        List<Job> jobs = jobService.searchJobsByKeyword(keywordTitle, keywordLocation);
        List<Map<String, String>> jobDetails = new ArrayList<>();
        for (Job job : jobs) {
            Map<String, String> details = new HashMap<>();
            details.put("url", "/position/" + job.getId());
            details.put("id", job.getId().toString());
            details.put("title", job.getTitle());
            details.put("location", job.getLocation());
            jobDetails.add(details);
        }
        return ResponseEntity.ok(jobDetails);
    }



    /**
     * Retrieves a job by its ID.
     * This method retrieves a job by its ID, validates the session, and returns the job
     * if found or a 404 Not Found status if the job does not exist.
     *
     * @param id The ID of the job to retrieve.
     * @return ResponseEntity containing the job if found, or a 404 Not Found status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {

        checkSession();

        Job job = jobService.getJobById(id);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job);
    }

    /**
     * Checks the session for a valid API key.
     * This method verifies that the session contains a valid API key. If the API key is missing
     * or invalid, it throws an ApiException with a 401 Unauthorized status.
     *
     * @throws ApiException if the API key is missing or invalid.
     */
    private void checkSession()
    {
        Object apiKey = httpSession.getAttribute("apiKey");

        if (apiKey == null || !clientService.isValidApiKey(apiKey.toString())) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid API Key");
        }
    }
}
