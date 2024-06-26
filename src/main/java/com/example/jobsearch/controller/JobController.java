package com.example.jobsearch.controller;

import com.example.jobsearch.exception.ApiException;
import com.example.jobsearch.model.Job;

import com.example.jobsearch.service.ClientService;
import com.example.jobsearch.service.JobService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

        String apiKey = httpSession.getAttribute("apiKey").toString();

        if (!clientService.isValidApiKey(apiKey)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid API Key");
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key");
        }

        Job savedJob = jobService.createJob(job);

        String jobUrl = "/position/" + savedJob.getId();
        return ResponseEntity.ok(jobUrl);

    }

    @GetMapping("/search")
    public ResponseEntity<?> searchJobs(@RequestParam String keyword_title, @RequestParam String keyword_location) {

        String apiKey = httpSession.getAttribute("apiKey").toString();

        if (!clientService.isValidApiKey(apiKey)) {
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key");
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid API Key");
        }
        List<Job> jobs = jobService.searchJobsByKeyword(keyword_title, keyword_location);
        List<Map<String, String>> jobDetails = new ArrayList<>();
        for (Job job : jobs) {
            Map<String, String> details = new HashMap<>();
            details.put("url", "/position/" + job.getId());
            details.put("title", job.getTitle());
            details.put("location", job.getLocation());
            jobDetails.add(details);
        }
        return ResponseEntity.ok(jobDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job);
    }
}
