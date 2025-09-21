package  com.dreamjob.jobseeker.controller;

import com.dreamjob.jobseeker.model.JobSeeker;
import com.dreamjob.jobseeker.service.JobSeekerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobseekers")
public class JobSeekerController {
    private final JobSeekerService service;

    public JobSeekerController(JobSeekerService service) { this.service = service; }

    @GetMapping("/ping")
    public String ping() {
        return "jjobseekers is alive!";
    }

    @PostMapping("/register")
    public JobSeeker register(@RequestBody JobSeeker employer) {
        return service.register(employer);
    }

    @PutMapping("/{id}")
    public JobSeeker updateJobSeeker(@RequestBody JobSeeker jobSeeker) {
        return service.updateJobSeeker(jobSeeker);
    }

    @GetMapping("/{id}")
    public JobSeeker getJobSeeker(@RequestBody Long id) {
        return service.getJobSeeker(id);
    }

    @DeleteMapping("/{id}")
    public JobSeeker deleteJobSeeker(@RequestBody Long id) {
        return null;
    }
}
