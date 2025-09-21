package com.dreamjob.Jobopening.controller;
import com.dreamjob.Jobopening.model.JobOpening;
import com.dreamjob.Jobopening.service.JobOpeningService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-openings")
public class JobOpeningController {
    private final JobOpeningService service;

    public JobOpeningController(JobOpeningService service) { this.service = service; }

    @GetMapping("/ping")
    public String ping() {
        return "job-openings is alive!";
    }

   @PostMapping
   public JobOpening create(@RequestBody JobOpening jobOpening) {

        return service.create(jobOpening);
   }

   @GetMapping
   public List<JobOpening> findAllJobOpening() {
        return null;
   }


    @GetMapping("/{id}")
    public JobOpening findJobOpening(@PathVariable Integer id) {
        return null;
    }

    @GetMapping("/sereach")
    public List<JobOpening> searchJobOpening(@RequestParam String skill, @RequestParam String location) {
        return null;
    }

    @DeleteMapping("/{id}")
    public JobOpening deleteJobOpening(@PathVariable Integer id) {
        return null;
    }
}
