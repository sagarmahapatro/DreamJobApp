package com.dreamjob.Jobopening.service;

import com.dreamjob.Jobopening.event.JobOpeningEvent;


public interface EventEmitter<T extends JobOpeningEvent> {
    void produce( T jobOpeningEvent);
}
