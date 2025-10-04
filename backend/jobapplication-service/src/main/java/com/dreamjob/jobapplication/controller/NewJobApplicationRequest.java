package com.dreamjob.jobapplication.controller;

public class NewJobApplicationRequest {
    private String jobSeekerId;
    private String JobTitle;
    private String jobSeekerEmailId;
    private String jobOpeningId;
    private String resumeText;

    public NewJobApplicationRequest() {
    }

    public NewJobApplicationRequest(String jobSeekerId, String JobTitle, String jobSeekerEmailId, String jobOpeningId, String resumeText) {
        this.jobSeekerId = jobSeekerId;
        this.JobTitle = JobTitle;
        this.jobSeekerEmailId = jobSeekerEmailId;
        this.jobOpeningId = jobOpeningId;
        this.resumeText = resumeText;
    }
    public String getJobSeekerId() {
        return jobSeekerId;
    }
    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }
    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.JobTitle = jobTitle;
    }
    public String getJobSeekerEmailId() {
        return jobSeekerEmailId;
    }
    public void setJobSeekerEmailId(String jobSeekerEmailId) {
        this.jobSeekerEmailId = jobSeekerEmailId;
    }
    public String getJobOpeningId() {
        return jobOpeningId;
    }
    public void setJobOpeningId(String jobOpeningId) {
        this.jobOpeningId = jobOpeningId;
    }
    public String getResumeText() {
        return resumeText;
    }
    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

}
