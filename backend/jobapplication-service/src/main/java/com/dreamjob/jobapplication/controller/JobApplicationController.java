package com.dreamjob.jobapplication.controller;

import com.dreamjob.jobapplication.model.JobApplication;
import com.dreamjob.jobapplication.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/" +
        "")
public class JobApplicationController {
    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) { this.service = service; }

    @GetMapping("/ping")
    public String ping() {
        return "applications-service is alive!";
    }

    @GetMapping
    public List<JobApplication> getAllJobSeekerApplications() {
        return service.getAllJobSeekerApplications();
    }

    @PostMapping
    public JobApplication apply(@RequestBody JobApplication application) {
        return service.apply(application);
    }

    @GetMapping("/{id}")
    public JobApplication getJobApplication(@PathVariable Long id) {
        return service.getJobApplication(id);
    }

    @PutMapping("/{id}")
    public JobApplication updateJobApplication(@PathVariable Long id, @RequestBody JobApplication application) {
        return service.updateJobApplication(id, application);
    }

    @DeleteMapping("/{id}")
    public JobApplication deleteJobApplication(@PathVariable Long id) {
        return service.deleteJobApplicationById(id);
    }


    @GetMapping("job-seeker/{jobseekerId}")
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
        JobApplication application = service.getJobApplication(applicationId);
        application.setId(applicationId);
        application.setStatus(status);
        return service.update(application); // Assuming register method updates if ID exists
    }
}
