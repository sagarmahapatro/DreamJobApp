package com.dreamjob.Jobopening.event;

public class JobOpeningEvent {

    private String jobOpeningId;
    private String title;
    private String description;
    private String employerId;

    public JobOpeningEvent() {}
    public JobOpeningEvent(String jobOpeningId, String title, String description, String employerId) {
        this.jobOpeningId = jobOpeningId;
        this.title = title;
        this.description = description;
        this.employerId = employerId;
    }

    public String getJobOpeningId() {
        return jobOpeningId;
    }

    public void setJobOpeningId(String jobOpeningId) {
        this.jobOpeningId = jobOpeningId;
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

    public JobOpeningEvent copy(JobOpeningEvent event) {
        this.jobOpeningId = event.jobOpeningId;
        this.title = event.title;
        this.description = event.description;
        this.employerId = event.employerId;
        return event;
    }
}
