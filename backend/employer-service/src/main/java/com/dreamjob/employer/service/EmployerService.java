package  com.dreamjob.employer.service;
import com.dreamjob.employer.dto.JobDescription;
import com.dreamjob.employer.model.Employer;
import com.dreamjob.employer.repository.EmployerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {
    private final EmployerRepository repo;
   public EmployerService(EmployerRepository repo) {
        this.repo = repo;
    }

    public Employer register(Employer e) {
        Employer saved = repo.save(e);
        return saved;

    }

    public Employer createEmployer(Employer employer) {
        Employer saved = repo.save(employer);
        return saved;
    }

    public List<Employer> getAllEmployers() {
       return repo.findAll();
    }

    public Employer getEmployerById(Long id) {
        Optional<Employer> empOpt = repo.findById(id);
        if(empOpt.isPresent()) {
            Employer emp = empOpt.get();
            return emp;
        } else {
            throw new RuntimeException("Employer not found with id: " + id);
        }
    }

    public Employer updateEmployerDetail(Long id, Employer employer) {
        Optional<Employer> empOpt = repo.findById(id);
        if(empOpt.isPresent()) {
            Employer emp = empOpt.get();
            emp.update(employer);
            return repo.save(emp);
        } else {
            throw new RuntimeException("Employer not found with id: " + id);
        }
    }

    public Employer deleteEmployerById(Long id) {
        Optional<Employer> empOpt = repo.findById(id);
        if(empOpt.isPresent()) {
            Employer emp = empOpt.get();
            repo.deleteById(id);
            return emp;
        } else {
            throw new RuntimeException("Employer not found with id: " + id);
        }

    }
}
