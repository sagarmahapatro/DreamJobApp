package com.dreamjob.configserver.controller;

import com.dreamjob.configserver.model.Application;
import com.dreamjob.configserver.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService service;

    public ApplicationController(ApplicationService service) { this.service = service; }

    @GetMapping("/ping")
    public String ping() {
        return "applications-service is alive!";
    }

    @PostMapping
    public Application apply(@RequestBody Application application) {
        return service.apply(application);
    }

    @GetMapping("jobseeker/{jobseekerId}")
    public List<Application> getAllByJobseekerApplications(@PathVariable Long jobseekerId) {
        return service.getAllByJobseeker(jobseekerId);
    }

    @GetMapping("/employer/{employerId}")
    public List<Application> getAllByEmployerApplications(@PathVariable Long employerId) {
        return service.getAllByEmployer(employerId);
    }

    @PutMapping("/{applicationId}/status")
    public Application updateApplicationStatus(@PathVariable Long applicationId, @RequestParam String status) {
        // Implement the logic to update the application status
        // This is a placeholder implementation
        Application application = new Application();
        application.setId(applicationId);
        application.setStatus(status);
        return service.update(application); // Assuming register method updates if ID exists
    }
}
