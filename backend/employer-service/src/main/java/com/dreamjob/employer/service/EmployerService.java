package  com.dreamjob.employer.service;
import com.dreamjob.employer.model.Employer;
import com.dreamjob.employer.repository.EmployerRepository;
import org.springframework.stereotype.Service;

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
}
