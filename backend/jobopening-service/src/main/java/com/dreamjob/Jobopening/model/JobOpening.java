package com.dreamjob.Jobopening.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Document(collection = "job_openings")
public class JobOpening {

    @Id
    private UUID id = UUID.randomUUID();

    private Long employerId;
    private String title;
    private String description;
    private String location;
    private String status;  // OPEN, CLOSED, FILLED

    private Set<String> requiredSkills = new HashSet<>();

    public JobOpening() {}

    public JobOpening(Long employerId, String title, String description, String location, String status) {
        this.employerId = employerId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.status = status;
    }

    // Getters & setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Long getEmployerId() { return employerId; }
    public void setEmployerId(Long employerId) { this.employerId = employerId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Set<String> getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(Set<String> requiredSkills) { this.requiredSkills = requiredSkills; }
}
