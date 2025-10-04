package com.dreamjob.jobapplication.model;

import java.util.Date;

public class ApplicationStatusHistoryChange {
    private ApplicationStatus status;
    private Date date;
    private String  description;

    public ApplicationStatusHistoryChange(ApplicationStatus status, String description) {
        this(status, new Date(), description);
    }

    public ApplicationStatusHistoryChange(ApplicationStatus status) {
        this(status, new Date(), "");
    }

    public ApplicationStatusHistoryChange(ApplicationStatus status, Date date) {
        this(status, date, "");
    }

    public ApplicationStatusHistoryChange(ApplicationStatus status, Date date, String description) {
        this.status = status;
        this.date = date;
        this.description = description;
    }
    public ApplicationStatus getStatus() {
        return status;
    }
    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
