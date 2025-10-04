package com.dreamjob.jobapplication.controller;

import com.dreamjob.jobapplication.model.ApplicationStatus;

public class StatusUpdateRequest {

    private ApplicationStatus status;
    private String  description;

    public StatusUpdateRequest() {}

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
