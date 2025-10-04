package com.dreamjob.jobseeker.service;

import com.dreamjob.jobseeker.event.JobSeekerCreatedEvent;
import com.dreamjob.jobseeker.event.JobSeekerDeleteEvent;
import com.dreamjob.jobseeker.event.JobSeekerEvent;
import com.dreamjob.jobseeker.event.JobSeekerUpdatedEvent;

public interface  EventEmitter<T extends JobSeekerEvent> {
    void produce( T jobSeekerEvent);
}
