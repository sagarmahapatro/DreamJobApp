package com.dreamjob.Jobopening.event.kafka.producer;

import com.dreamjob.Jobopening.event.JobOpeningEvent;
import com.dreamjob.Jobopening.service.EventEmitter;

public interface JobOpeningEventProducer<T extends JobOpeningEvent> extends EventEmitter<T> {
    Class<T> getEventType();
}
