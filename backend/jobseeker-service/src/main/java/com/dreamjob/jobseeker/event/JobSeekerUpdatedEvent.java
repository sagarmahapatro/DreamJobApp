package com.dreamjob.jobseeker.event;

public class JobSeekerUpdatedEvent extends JobSeekerEvent {

    public JobSeekerUpdatedEvent copy(JobSeekerUpdatedEvent event) {
           super.copy(event);
        return this;
    }
}
