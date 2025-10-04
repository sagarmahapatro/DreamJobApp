package com.dreamjob.jobapplication.model;

import java.util.List;

public class ApplicationStatusHistory {
    private List<ApplicationStatusHistoryChange> applicationStatus;

    public ApplicationStatusHistory() {
        this.applicationStatus = new java.util.ArrayList<>();
    }
    public List<ApplicationStatusHistoryChange> getApplicationStatus() {
        return applicationStatus;
    }
     public void setApplicationStatus(List<ApplicationStatusHistoryChange> applicationStatus) {
        this.applicationStatus = applicationStatus;
     }

     public ApplicationStatusHistoryChange currentStatus() {
        if (applicationStatus == null || applicationStatus.isEmpty()) {
            return null;
        }
        return applicationStatus.get(applicationStatus.size() - 1);
     }

     public void setCurrentStatus(ApplicationStatusHistoryChange currentStatus) {
        this.applicationStatus.add(currentStatus);
     }

    public void addStatus(ApplicationStatus applicationStatus, String description) {
        this.applicationStatus.add(new ApplicationStatusHistoryChange(applicationStatus, description));
    }
}
