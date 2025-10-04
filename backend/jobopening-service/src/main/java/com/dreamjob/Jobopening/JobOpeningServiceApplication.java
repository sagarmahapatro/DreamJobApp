package com.dreamjob.Jobopening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JobOpeningServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobOpeningServiceApplication.class, args);
    }
}
