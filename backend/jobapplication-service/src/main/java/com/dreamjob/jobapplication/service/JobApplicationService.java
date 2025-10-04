package  com.dreamjob.jobapplication.service;

import com.dreamjob.jobapplication.event.jobopening.JobOpeningCreatEvent;
import com.dreamjob.jobapplication.event.jobopening.JobOpeningDeleteEvent;
import com.dreamjob.jobapplication.event.jobopening.JobOpeningUpdateEvent;
import com.dreamjob.jobapplication.event.jobseeker.JobSeekerCreatEvent;
import com.dreamjob.jobapplication.event.jobseeker.JobSeekerDeleteEvent;
import com.dreamjob.jobapplication.event.jobseeker.JobSeekerUpdateEvent;
import com.dreamjob.jobapplication.model.ApplicationStatus;
import com.dreamjob.jobapplication.model.JobApplication;
import com.dreamjob.jobapplication.model.JobOpening;
import com.dreamjob.jobapplication.model.JobSeeker;
import com.dreamjob.jobapplication.repository.JobApplicationRepository;
import com.dreamjob.jobapplication.repository.JobOpeningRepository;
import com.dreamjob.jobapplication.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobOpeningRepository jobOpeningRepository;
    private final JobSeekerRepository jobSeekerRepository;

    @Autowired
    public JobApplicationService(JobApplicationRepository jobApplicationRepository, JobOpeningRepository jobOpeningRepository, JobSeekerRepository jobSeekerRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobOpeningRepository = jobOpeningRepository;
        this.jobSeekerRepository = jobSeekerRepository;
    }

    public JobApplication register(JobApplication e) {
        JobApplication saved = jobApplicationRepository.save(e);
        return saved;
    }

    public JobApplication apply(JobApplication application) {
        application.apply();
        JobApplication saved = jobApplicationRepository.save(application);
        return saved;
    }

    public List<JobApplication> getAllByJobseeker(Long jobseekerId) {
        Optional<JobSeeker> job = jobSeekerRepository.findJobSeekerByJobSeekerId(String.valueOf(jobseekerId));
        if(job.isPresent()) {
            JobSeeker js = job.get();
            return jobApplicationRepository.findByJobSeekerId(js.getId());
        } else {
            throw new RuntimeException("JobSeeker not found with id: " + jobseekerId);
        }
    }

    public List<JobApplication> getAllByEmployer(Long employerId) {
        List<JobOpening> jobs = jobOpeningRepository.findJobOpeningByEmployerId(String.valueOf(employerId));
        if(jobs != null && jobs.size() > 0) {
            return jobs.stream().map( job -> jobApplicationRepository.findByJobOpeningId(job.getId())).flatMap(List::stream).toList();
        } else {
            throw new RuntimeException("employerId not found with id: " + employerId);
        }
    }


    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    public JobApplication getJobApplication(String id) {
        Optional<JobApplication> jobApplicationOpt = jobApplicationRepository.findByApplicationId(id);
        if(jobApplicationOpt.isPresent()) {
            return jobApplicationOpt.get();
        } else {
            throw new RuntimeException("JobApplication not found with id: " + id);
        }
    }

    public JobApplication deleteJobApplicationById(String id) {
        Optional<JobApplication> JobApplicationOpt = jobApplicationRepository.findByApplicationId(id);
        if(JobApplicationOpt.isPresent()) {
            JobApplication jobApplication = JobApplicationOpt.get();
            jobApplicationRepository.deleteById(jobApplication.getId());
            return jobApplication;
        } else {
            throw new RuntimeException("JobApplication not found with id: " + id);
        }
    }

    public void processJobOpeningCreatedEvent(JobOpeningCreatEvent created) {
        JobOpening jobOpening = new JobOpening(created.getJobOpeningId(), created.getTitle(), created.getDescription(), created.getEmployerId());
        jobOpeningRepository.save(jobOpening);
    }

    public void processJobOpeningUpdateEvent(JobOpeningUpdateEvent updated) {
        Optional<JobOpening> jobOpeningOpt = jobOpeningRepository.findJobOpeningByJobOpeningId(updated.getJobOpeningId());
        if(jobOpeningOpt.isPresent()) {
            JobOpening jobOpening = jobOpeningOpt.get();
            jobOpening.setTitle(updated.getTitle());
            jobOpening.setDescription(updated.getDescription());
            jobOpeningRepository.save(jobOpening);
        } else {
            throw new RuntimeException("JobOpening not found with id: " + updated.getJobOpeningId());
        }
    }

    public void processJobOpeningDeleteEvent(JobOpeningDeleteEvent deleted) {
        Optional<JobOpening> jobOpeningOpt = jobOpeningRepository.findJobOpeningByJobOpeningId(deleted.getJobOpeningId());
        if(jobOpeningOpt.isPresent()) {
            JobOpening jobOpening = jobOpeningOpt.get();
            jobOpeningRepository.deleteById(jobOpening.getId());
        } else {
            throw new RuntimeException("JobOpening not found with id: " + deleted.getJobOpeningId());
        }
    }

    public void processJobSeekerCreatEvent(JobSeekerCreatEvent created) {
        JobSeeker jobSeeker = new JobSeeker(created.getJobSeekerId(), created.getName(), created.getEmail());
        jobSeekerRepository.save(jobSeeker);
    }

    public void processJobSeekerUpdateEvent(JobSeekerUpdateEvent updated) {
        Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findJobSeekerByJobSeekerId(updated.getJobSeekerId());
        if(jobSeekerOpt.isPresent()) {
            JobSeeker jobSeeker = jobSeekerOpt.get();
            jobSeeker.setName(updated.getName());
            jobSeeker.setEmail(updated.getEmail());
            jobSeekerRepository.save(jobSeeker);
        } else {
            throw new RuntimeException("JobSeeker not found with id: " + updated.getJobSeekerId());
        }
    }

    public void processJobSeekerDeleteEvent(JobSeekerDeleteEvent deleted) {
        Optional<JobSeeker> jobSeekerOpt = jobSeekerRepository.findJobSeekerByJobSeekerId(deleted.getJobSeekerId());
        if(jobSeekerOpt.isPresent()) {
            JobSeeker jobSeeker = jobSeekerOpt.get();
            jobSeekerRepository.deleteById(jobSeeker.getId());
        } else {
            throw new RuntimeException("JobSeeker not found with id: " + deleted.getJobSeekerId());
        }
    }

    public JobApplication updateStatus(Long applicationId, ApplicationStatus status, String description) {
        Optional<JobApplication> jobApplicationOpt = jobApplicationRepository.findByApplicationId(String.valueOf(applicationId));
        if(jobApplicationOpt.isPresent()) {
            JobApplication jobApplication = jobApplicationOpt.get();
            jobApplication.getApplicationStatusHistory().addStatus(status, description);
            return jobApplicationRepository.save(jobApplication);
        } else {
            throw new RuntimeException("JobApplication not found with id: " + applicationId);
        }
    }
}
