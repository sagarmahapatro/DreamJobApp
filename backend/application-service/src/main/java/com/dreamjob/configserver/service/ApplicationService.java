package  com.dreamjob.configserver.service;

import com.dreamjob.configserver.model.Application;
import com.dreamjob.configserver.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository repo;

    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public Application register(Application e) {
        Application saved = repo.save(e);

        return saved;
    }

    public Application apply(Application application) {
        return null;
    }

    public List<Application> getAllByJobseeker(Long jobseekerId) {
        return null;
    }

    public List<Application> getAllByEmployer(Long employerId) {
        return null;
    }

    public Application update(Application application) {
        return null;
    }
}
