package com.dreamjob.jobapplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "job_seeker")
public class JobSeeker {

    @Id
    private UUID id;

    private String jobSeekerId;
    private String resumeSummery;
    private String name;
    private String email;

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public JobSeeker() {}

    public JobSeeker(String jobSeekerId, String name, String email) {
        this.jobSeekerId = jobSeekerId;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getResumeSummery() {
        return resumeSummery;
    }

    public void setResumeSummery(String resumeSummery) {
        this.resumeSummery = resumeSummery;
    }

}
