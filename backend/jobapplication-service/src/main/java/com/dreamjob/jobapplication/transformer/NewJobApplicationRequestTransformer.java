package com.dreamjob.jobapplication.transformer;

import com.dreamjob.jobapplication.controller.NewJobApplicationRequest;
import com.dreamjob.jobapplication.model.JobApplication;
import com.dreamjob.jobapplication.model.JobOpening;
import com.dreamjob.jobapplication.model.JobSeeker;
import com.dreamjob.jobapplication.repository.JobOpeningRepository;
import com.dreamjob.jobapplication.repository.JobSeekerRepository;
import com.dreamjob.transformer.Transformer;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class NewJobApplicationRequestTransformer implements Transformer<NewJobApplicationRequest, JobApplication> {

    private final JobOpeningRepository jobOpeningRepository;
    private final JobSeekerRepository jobSeekerRepository;

    public NewJobApplicationRequestTransformer(JobOpeningRepository jobOpeningRepository, JobSeekerRepository jobSeekerRepository) {
        this.jobOpeningRepository = jobOpeningRepository;
        this.jobSeekerRepository = jobSeekerRepository;
    }
    @Override
    public JobApplication transform(NewJobApplicationRequest source) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJobSeekerId(findJobSeeker(source).getId());
        jobApplication.setJobOpeningId(findJobOpening(source).getId());
        jobApplication.setAppliedAt(LocalDateTime.now());
        return jobApplication;
    }

    private JobOpening findJobOpening(NewJobApplicationRequest source) {
        Optional<JobOpening> job = null;
        if(StringUtils.isNotBlank(source.getJobOpeningId())){
            job  = jobOpeningRepository.findJobOpeningByJobOpeningId(source.getJobOpeningId());

        } else if(StringUtils.isNotBlank(source.getJobTitle())){
            job  = jobOpeningRepository.findJobOpeningByTitle(source.getJobTitle());
        }
        if(job.isPresent()) {
            return job.get();
        }
        else {
            throw new IllegalArgumentException("JobOpening not found with id: " + source.getJobOpeningId()
                    + " or title: " + source.getJobTitle());
        }
    }

    private JobSeeker findJobSeeker(NewJobApplicationRequest request){
        Optional<JobSeeker> job = null;
        if(StringUtils.isNotBlank(request.getJobSeekerId())){
             job  = jobSeekerRepository.findJobSeekerByJobSeekerId(request.getJobSeekerId());

        } else if(StringUtils.isNotBlank(request.getJobSeekerEmailId())){
            job  = jobSeekerRepository.findJobSeekerByEmail(request.getJobSeekerEmailId());
        }

        if(job.isPresent()) {
            return job.get();
        }
        else {
            throw new IllegalArgumentException("JobSeeker not found with id: " + request.getJobSeekerId()
            + " or email: " + request.getJobSeekerEmailId());
        }
    }
}
