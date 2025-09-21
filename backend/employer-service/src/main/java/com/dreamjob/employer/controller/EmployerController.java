package  com.dreamjob.employer.controller;
import com.dreamjob.employer.dto.JobDescription;
import com.dreamjob.employer.model.Employer;
import com.dreamjob.employer.service.EmployerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employers")
public class EmployerController {
    private final EmployerService service;

    public EmployerController(EmployerService service) { this.service = service; }

    @GetMapping("/ping")
    public String ping() {
        return "employer-service is alive!";
    }

    @PostMapping("/register")
    public Employer register(@RequestBody Employer employer) {
        return service.register(employer);
    }

    @PostMapping("/{id}/jobs")
    public JobDescription postJobOpening(@PathVariable Integer id, @RequestBody JobDescription jobDescription) {
        return null;
    }

    @GetMapping("/{id}/jobs")
    public List<JobDescription> getJobs(@PathVariable Integer id) {
        return null;
    }

    @DeleteMapping("/jobs/{jobId}")
    public JobDescription deleteJob(@PathVariable Integer jobId) {
        return null;
    }
}
