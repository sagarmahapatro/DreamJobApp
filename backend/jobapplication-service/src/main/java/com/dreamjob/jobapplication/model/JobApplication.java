package com.dreamjob.jobapplication.model;

import com.netflix.discovery.shared.Application;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "job_applications")
public class JobApplication {

    @Id
    private UUID id;

    @Indexed(unique = true)
    private String applicationId;

    private UUID jobSeekerId; // Reference to JobSeeker entity
    private UUID jobOpeningId; // Reference to JobOpening entity
    private LocalDateTime appliedAt;
    private ApplicationStatusHistory applicationStatusHistory = new ApplicationStatusHistory();


    public JobApplication() {}
    public JobApplication(UUID jobSeekerId, UUID jobOpeningId) {
        this.jobSeekerId = jobSeekerId;
        this.jobOpeningId = jobOpeningId;
        this.appliedAt = LocalDateTime.now();
    }

    public void apply(){
        this.applicationStatusHistory.addStatus(ApplicationStatus.APPLIED, "candidate has applied for the job");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(UUID jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public UUID getJobOpeningId() {
        return jobOpeningId;
    }

    public void setJobOpeningId(UUID jobOpeningId) {
        this.jobOpeningId = jobOpeningId;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }


    public ApplicationStatusHistory getApplicationStatusHistory() {
        return applicationStatusHistory;
    }

    public void setApplicationStatusHistory(ApplicationStatusHistory applicationStatusHistory) {
        this.applicationStatusHistory = applicationStatusHistory;
    }


    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}

