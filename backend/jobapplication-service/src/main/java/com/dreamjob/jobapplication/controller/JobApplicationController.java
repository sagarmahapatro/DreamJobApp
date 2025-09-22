package com.dreamjob.jobapplication.controller;

import com.dreamjob.jobapplication.model.JobApplication;
import com.dreamjob.jobapplication.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-applications")
public class JobApplicationController {
    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) { this.service = service; }

    @GetMapping("/ping")
    public String ping() {
        return "applications-service is alive!";
    }

    @PostMapping
    public JobApplication apply(@RequestBody JobApplication application) {
        return service.apply(application);
    }

    @GetMapping("jobseeker/{jobseekerId}")
    public List<JobApplication> getAllByJobseekerApplications(@PathVariable Long jobseekerId) {
        return service.getAllByJobseeker(jobseekerId);
    }

    @GetMapping("/employer/{employerId}")
    public List<JobApplication> getAllByEmployerApplications(@PathVariable Long employerId) {
        return service.getAllByEmployer(employerId);
    }

    @PutMapping("/{applicationId}/status")
    public JobApplication updateApplicationStatus(@PathVariable Long applicationId, @RequestParam String status) {
        // Implement the logic to update the application status
        // This is a placeholder implementation
        JobApplication application = new JobApplication();
        application.setId(applicationId);
        application.setStatus(status);
        return service.update(application); // Assuming register method updates if ID exists
    }
}
