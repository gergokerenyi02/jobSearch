package com.example.jobsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableSwagger2
@EntityScan("com.example.jobsearch.model")
@EnableJpaRepositories(basePackages = {"com.example.jobsearch"})
public class JobSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobSearchApplication.class, args);
    }

}
