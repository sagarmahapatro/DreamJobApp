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

    @PostMapping
    Employer createEmployer(@RequestBody Employer employer) {
        return service.createEmployer(employer);
    }

    @GetMapping
    List<Employer> getAllEmployers() {
        return service.getAllEmployers();
    }
    @GetMapping("/{id}")
    Employer getEmployer(@PathVariable Long id) {
        return service.getEmployerById(id);
    }

    @PutMapping("/{id}")
    Employer updateEmployerDetail(@PathVariable Long id, @RequestBody Employer employer) {
        return service.updateEmployerDetail(id, employer);
    }

    @DeleteMapping("/{id}")
    public Employer deleteEmployer(@PathVariable Long id) {
        return service.deleteEmployerById(id);
    }



}
