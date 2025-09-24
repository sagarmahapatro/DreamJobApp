package  com.dreamjob.jobapplication.service;

import com.dreamjob.jobapplication.model.JobApplication;
import com.dreamjob.jobapplication.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {
    private final JobApplicationRepository repo;

    public JobApplicationService(JobApplicationRepository repo) {
        this.repo = repo;
    }

    public JobApplication register(JobApplication e) {
        JobApplication saved = repo.save(e);

        return saved;
    }

    public JobApplication apply(JobApplication application) {
        return null;
    }

    public List<JobApplication> getAllByJobseeker(Long jobseekerId) {
        return null;
    }

    public List<JobApplication> getAllByEmployer(Long employerId) {
        return null;
    }

    public JobApplication update(JobApplication application) {
        return null;
    }

    public List<JobApplication> getAllJobSeekerApplications() {
        return null;
    }

    public JobApplication getJobApplication(Long id) {
        return null;
    }

    public JobApplication updateJobApplication(Long id, JobApplication application) {
        return null;
    }

    public JobApplication deleteJobApplicationById(Long id) {
        return null;
    }
}
