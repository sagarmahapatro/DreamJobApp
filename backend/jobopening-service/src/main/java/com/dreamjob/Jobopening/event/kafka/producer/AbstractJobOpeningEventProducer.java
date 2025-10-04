package com.dreamjob.Jobopening.event.kafka.producer;

import com.dreamjob.Jobopening.config.KafkaProducerConfig;
import com.dreamjob.Jobopening.event.JobOpeningEvent;
import com.dreamjob.event.EventConfigProperties;

public abstract class AbstractJobOpeningEventProducer<T extends JobOpeningEvent> implements JobOpeningEventProducer<T> {

    protected final EventConfigProperties eventConfigProperties;
    protected final KafkaProducerConfig config;

    public AbstractJobOpeningEventProducer(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        this.config = config;
        this.eventConfigProperties = eventConfigProperties;
    }
}
