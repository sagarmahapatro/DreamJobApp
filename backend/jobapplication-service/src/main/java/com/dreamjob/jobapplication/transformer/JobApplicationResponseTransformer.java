package com.dreamjob.jobapplication.transformer;

import com.dreamjob.jobapplication.controller.JobApplicationResponse;
import com.dreamjob.jobapplication.model.ApplicationStatusHistoryChange;
import com.dreamjob.jobapplication.model.JobApplication;
import com.dreamjob.jobapplication.model.JobOpening;
import com.dreamjob.jobapplication.model.JobSeeker;
import com.dreamjob.jobapplication.repository.JobOpeningRepository;
import com.dreamjob.jobapplication.repository.JobSeekerRepository;
import com.dreamjob.transformer.Transformer;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class JobApplicationResponseTransformer implements Transformer<JobApplication, JobApplicationResponse> {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    private final JobOpeningRepository jobOpeningRepository;
    private final JobSeekerRepository jobSeekerRepository;

    public JobApplicationResponseTransformer(JobOpeningRepository jobOpeningRepository, JobSeekerRepository jobSeekerRepository) {
        this.jobOpeningRepository = jobOpeningRepository;
        this.jobSeekerRepository = jobSeekerRepository;
    }
    @Override
    public JobApplicationResponse transform(JobApplication source) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(source.getJobSeekerId()).orElse(null);
        JobOpening jobOpening = jobOpeningRepository.findById(source.getJobOpeningId()).orElse(null);
        if (jobSeeker != null && jobOpening != null) {
            String applicationId = source.getApplicationId();
            String jobSeekerName = jobSeeker.getName();
            String jobSeekerId =  jobSeeker.getJobSeekerId();
            String jobSeekerEmailId = jobSeeker.getEmail();
            String jobOpeningId = jobOpening.getJobOpeningId();
            String jobOpeningTitle = jobOpening.getTitle();
            ApplicationStatusHistoryChange applicationStatusHistoryChange = source.getApplicationStatusHistory().currentStatus();
            String applicationStatus = applicationStatusHistoryChange.getStatus().name();
            String applicationStatusChangeDate = DATE_FORMAT.format(applicationStatusHistoryChange.getDate());
            String applicationDate = DATE_FORMAT.format(source.getAppliedAt());
            String resumeSummery = jobSeeker.getResumeSummery();
            return new JobApplicationResponse(
                    applicationId,
                    jobSeekerName,
                    jobSeekerId,
                    jobSeekerEmailId,
                    jobOpeningId,
                    jobOpeningTitle,
                    applicationStatus,
                    applicationStatusChangeDate,
                    applicationDate,
                    resumeSummery
            );
        }
        return null;
    }
}
