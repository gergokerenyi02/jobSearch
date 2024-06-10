package com.example.jobsearch.repository;


import com.example.jobsearch.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

@Component
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitleContainingOrLocationContaining(String titleKeyword, String locationKeyword);
}
