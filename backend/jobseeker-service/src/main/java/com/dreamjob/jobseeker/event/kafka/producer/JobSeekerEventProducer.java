package com.dreamjob.jobseeker.event.kafka.producer;

import com.dreamjob.jobseeker.event.JobSeekerEvent;
import com.dreamjob.jobseeker.service.EventEmitter;

public interface JobSeekerEventProducer<T extends JobSeekerEvent> extends EventEmitter<T> {
    Class<T> getEventType();
}
