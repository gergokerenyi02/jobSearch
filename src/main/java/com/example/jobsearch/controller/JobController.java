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

    @PostMapping
    public ResponseEntity<?> createJob(@RequestBody Job job) {

        checkSession();

        Map<Object, String> response = new HashMap<>();

        Job savedJob = jobService.createJob(job);
        String jobUrl = "/position/" + savedJob.getId();

        response.put("status", "success");
        response.put("message", "Job created successfully!");
        response.put("jobUrl", jobUrl);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable String id) {
        jobService.deleteJob(Long.valueOf(id));
    }


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

    @GetMapping("/search")
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {

        checkSession();

        Job job = jobService.getJobById(id);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job);
    }

    private void checkSession()
    {
        Object apiKey = httpSession.getAttribute("apiKey");

        if (apiKey == null || !clientService.isValidApiKey(apiKey.toString())) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid API Key");
        }
    }
}
