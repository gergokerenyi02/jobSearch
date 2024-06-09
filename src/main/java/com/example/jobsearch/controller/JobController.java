package com.example.jobsearch.controller;

import com.example.jobsearch.model.Job;

import com.example.jobsearch.service.ClientService;
import com.example.jobsearch.service.JobService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

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
    public ResponseEntity<?> createJob(@ModelAttribute Job job) {

        String apiKey = httpSession.getAttribute("apiKey").toString();

        if (!clientService.isValidApiKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key");
        }

        Job savedJob = jobService.createJob(job);

        String jobUrl = "/position/" + savedJob.getTitle() + "-" + savedJob.getLocation() + "-" + savedJob.getId();
        return ResponseEntity.ok(jobUrl);

    }

    @GetMapping("/search")
    public ResponseEntity<?> searchJobs(@RequestParam String keyword) {
        List<Job> jobs = jobService.searchJobsByKeyword(keyword);
        return ResponseEntity.ok(jobs);
    }
}
