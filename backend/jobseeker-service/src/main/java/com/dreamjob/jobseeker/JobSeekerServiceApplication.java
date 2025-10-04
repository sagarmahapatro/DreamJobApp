package com.dreamjob.jobseeker;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class JobSeekerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobSeekerServiceApplication.class, args);

    }


}
