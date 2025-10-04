package com.dreamjob.jobseeker.event;

public class JobSeekerEvent {

    private String jobSeekerId;
    private String resumeSummery;
    private String name;
    private String email;

    public JobSeekerEvent() {}

    public JobSeekerEvent(String jobSeekerId, String resumeSummery, String name, String email) {
        this.jobSeekerId = jobSeekerId;
        this.resumeSummery = resumeSummery;
        this.name = name;
        this.email = email;
    }

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getResumeSummery() {
        return resumeSummery;
    }

    public void setResumeSummery(String resumeSummery) {
        this.resumeSummery = resumeSummery;
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

    public JobSeekerEvent clone() {
        return new JobSeekerEvent(this.jobSeekerId, this.resumeSummery, this.name, this.email);
    }

    public JobSeekerEvent copy(JobSeekerEvent event) {
        this.jobSeekerId = event.getJobSeekerId();
        this.resumeSummery = event.getResumeSummery();
        this.name = event.getName();
        this.email = event.getEmail();
        return event;
    }

}
