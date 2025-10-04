package com.dreamjob.jobapplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "job_opening")
public class JobOpening {

    @Id
    private UUID id;

    private String jobOpeningId;
    private String title;
    private String description;
    private String employerId;

    public JobOpening() {}
    public JobOpening(String jobOpeningId, String title, String description, String employerId) {
        this.jobOpeningId = jobOpeningId;
        this.title = title;
        this.description = description;
        this.employerId = employerId;
    }

    public UUID getId() {
        return id;
    }

    public String getJobOpeningId() {
        return jobOpeningId;
    }

    public void setJobOpeningId(String jobOpeningId) {
        this.jobOpeningId = jobOpeningId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }
    // Getters and Setters
}
