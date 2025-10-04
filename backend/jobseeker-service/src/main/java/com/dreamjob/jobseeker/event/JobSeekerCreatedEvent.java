package com.dreamjob.jobseeker.event;

import com.dreamjob.jobseeker.event.JobSeekerEvent;

public class JobSeekerCreatedEvent extends JobSeekerEvent {

    public JobSeekerCreatedEvent copy(JobSeekerCreatedEvent event) {
        super.copy(event);
        return this;
    }

}
