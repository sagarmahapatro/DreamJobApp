package com.dreamjob.jobapplication.controller;

public class JobApplicationResponse {
    private String applicationId;
    private String jobSeekerName;
    private String jobSeekerId;
    private String jobSeekerEmailId;
    private String jobOpeningId;
    private String jobOpeningTitle;
    private String applicationStatus;
    private String resumeSummery;
    private String applicationStatusChangeDate;
    private String applicationDate;

    public JobApplicationResponse(String applicationId, String jobSeekerName,
                                  String jobSeekerId,
                                  String jobSeekerEmailId,
                                  String jobOpeningId,
                                  String jobOpeningTitle,
                                  String applicationStatus,
                                  String applicationStatusChangeDate,
                                  String applicationDate,
                                  String resumeSummery) {
        this.applicationId = applicationId;
        this.jobSeekerName = jobSeekerName;
        this.jobSeekerId = jobSeekerId;
        this.jobSeekerEmailId = jobSeekerEmailId;
        this.jobOpeningId = jobOpeningId;
        this.jobOpeningTitle = jobOpeningTitle;
        this.applicationStatus = applicationStatus;
        this.applicationStatusChangeDate = applicationStatusChangeDate;
        this.applicationDate = applicationDate;
        this.resumeSummery = resumeSummery;
    }

    public JobApplicationResponse() {
    }

    public String getJobSeekerName() {
        return jobSeekerName;
    }

    public void setJobSeekerName(String jobSeekerName) {
        this.jobSeekerName = jobSeekerName;
    }

    public String getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(String jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
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

    public String getJobOpeningTitle() {
        return jobOpeningTitle;
    }

    public void setJobOpeningTitle(String jobOpeningTitle) {
        this.jobOpeningTitle = jobOpeningTitle;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationStatusChangeDate() {
        return applicationStatusChangeDate;
    }

    public void setApplicationStatusChangeDate(String applicationStatusChangeDate) {
        this.applicationStatusChangeDate = applicationStatusChangeDate;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getResumeSummery() {
        return resumeSummery;
    }

    public void setResumeSummery(String resumeSummery) {
        this.resumeSummery = resumeSummery;
    }
}
