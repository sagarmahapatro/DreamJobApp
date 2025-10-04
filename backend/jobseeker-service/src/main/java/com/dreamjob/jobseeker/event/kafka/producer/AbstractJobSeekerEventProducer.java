package com.dreamjob.jobseeker.event.kafka.producer;

import com.dreamjob.event.EventConfigProperties;
import com.dreamjob.jobseeker.config.KafkaProducerConfig;
import com.dreamjob.jobseeker.event.JobSeekerEvent;

public abstract class AbstractJobSeekerEventProducer<T extends JobSeekerEvent> implements JobSeekerEventProducer<T> {

    protected final EventConfigProperties eventConfigProperties;
    protected final KafkaProducerConfig config;

    public AbstractJobSeekerEventProducer(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        this.config = config;
        this.eventConfigProperties = eventConfigProperties;
    }
}
