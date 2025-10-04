package com.dreamjob.jobapplication.controller;

import com.dreamjob.jobapplication.model.JobApplication;
import com.dreamjob.jobapplication.service.JobApplicationService;
import com.dreamjob.transformer.Transformer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("job-applications")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;
    private final Transformer<NewJobApplicationRequest, JobApplication> newJobApplicationRequestTransformer;
    private final Transformer<JobApplication, JobApplicationResponse> jobApplicationResponseTransformer;

    public JobApplicationController(JobApplicationService jobApplicationService,
                                    Transformer<NewJobApplicationRequest, JobApplication> newJobApplicationRequestTransformer,
                                    Transformer<JobApplication, JobApplicationResponse> jobApplicationResponseTransformer) {
        this.jobApplicationService = jobApplicationService;
        this.newJobApplicationRequestTransformer = newJobApplicationRequestTransformer;
        this.jobApplicationResponseTransformer = jobApplicationResponseTransformer;
    }

    @GetMapping("/ping")
    public String ping() {
        return "applications-service is alive!";
    }

    @GetMapping
    public List<JobApplicationResponse> getAllApplications() {
        return jobApplicationService.getAllApplications().stream()
                .map(jobApplicationResponseTransformer::transform)
                .toList();
    }

    @PostMapping
    public JobApplicationResponse apply(@RequestBody NewJobApplicationRequest newJobApplicationRequest) {
        return jobApplicationResponseTransformer.transform(jobApplicationService.apply(newJobApplicationRequestTransformer.transform(newJobApplicationRequest)));
    }

    @GetMapping("/{id}")
    public JobApplicationResponse getJobApplication(@PathVariable String id) {
        return jobApplicationResponseTransformer.transform(jobApplicationService.getJobApplication(id));
    }

    @DeleteMapping("/{id}")
    public JobApplicationResponse deleteJobApplication(@PathVariable String id) {
        return jobApplicationResponseTransformer.transform(jobApplicationService.deleteJobApplicationById(id));
    }

    @GetMapping("job-seeker/{jobseekerId}")
    public List<JobApplicationResponse> getAllByJobseekerApplications(@PathVariable Long jobseekerId) {
        return jobApplicationService.getAllByJobseeker(jobseekerId).stream().map( jobApplicationResponseTransformer::transform).toList();
    }

    @GetMapping("/employer/{employerId}")
    public List<JobApplicationResponse> getAllByEmployerApplications(@PathVariable Long employerId) {
        return jobApplicationService.getAllByEmployer(employerId).stream().map( jobApplicationResponseTransformer::transform).toList();
    }

    @PutMapping("/{applicationId}/status")
    public JobApplicationResponse updateApplicationStatus(@PathVariable Long applicationId, @RequestBody StatusUpdateRequest statusUpdateRequest) {
       return jobApplicationResponseTransformer.transform(jobApplicationService.updateStatus(applicationId, statusUpdateRequest.getStatus(), statusUpdateRequest.getDescription())); // Assuming register method updates if ID exists
    }
}
