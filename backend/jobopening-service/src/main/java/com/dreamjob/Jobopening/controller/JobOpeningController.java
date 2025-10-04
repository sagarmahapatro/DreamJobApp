package com.dreamjob.Jobopening.controller;
import com.dreamjob.Jobopening.model.JobOpening;
import com.dreamjob.Jobopening.model.SkillName;
import com.dreamjob.Jobopening.service.JobOpeningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/job-openings")
public class JobOpeningController {
    private final JobOpeningService service;

    public JobOpeningController(JobOpeningService service) { this.service = service; }

    @GetMapping("/ping")
    public String ping() {
        return "job-openings is alive!";
    }

    @GetMapping
    public List<JobOpening> findAllJobOpening() {
        return service.findAllJobOpening();
    }

    @PostMapping
    public JobOpening create(@RequestBody JobOpening jobOpening) {
        return service.create(jobOpening);
    }

    @GetMapping("/{id}")
    public JobOpening findJobOpening(@PathVariable UUID id) {
        return service.findJobOpening(id);
    }

    @DeleteMapping("/{id}")
    public JobOpening deleteJobOpening(@PathVariable UUID id) {
        return service.deleteJobOpening(id);
    }

    @PutMapping("/{id}")
    public JobOpening update(@PathVariable UUID id,@RequestBody JobOpening jobOpening) {
        return service.update(id, jobOpening);
    }

    @GetMapping("/employer/{id}")
    public List<JobOpening> getJobOpeningPostedByEmployer(@PathVariable Long id) {
        return service.getJobOpeningPostedByEmployer(id);
    }


     @GetMapping("/search")
    public List<JobOpening> search(
            @RequestParam List<SkillName> skills,
            @RequestParam String location,
            @RequestParam String status) {
        return service.search(skills, location, status);
    }


}
