package  com.dreamjob.jobseeker.service;

import com.dreamjob.jobseeker.model.JobSeeker;
import com.dreamjob.jobseeker.repository.JobSeekerRepository;
import org.springframework.stereotype.Service;

@Service
public class JobSeekerService {
    private final JobSeekerRepository repo;

   public JobSeekerService(JobSeekerRepository repo) {
        this.repo = repo;
   }

    public JobSeeker register(JobSeeker e) {
        JobSeeker saved = repo.save(e);

        return saved;
    }

    public JobSeeker updateJobSeeker(JobSeeker jobSeeker) {
       return null;
    }

    public JobSeeker getJobSeeker(Long id) {
       return null;
    }
}
