package com.dreamjob.Jobopening.event.kafka.producer;

import com.dreamjob.Jobopening.config.KafkaProducerConfig;
import com.dreamjob.Jobopening.event.JobOpeningDeletedEvent;
import com.dreamjob.event.EventConfigProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JobSeekerEventProducerFactory {

    private final EventConfigProperties eventConfigProperties;
    private final KafkaProducerConfig config;

    private Map<Class, JobOpeningEventProducer> registry;

    public JobSeekerEventProducerFactory(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        this.config = config;
        this.eventConfigProperties = eventConfigProperties;
        registry = new HashMap<>();
        registerJobSeekerEventProducer();
    }

    private void registerJobSeekerEventProducer() {
        NewJobOpeningEventProducer newJobOpeningEventProducer = new NewJobOpeningEventProducer(config, eventConfigProperties);
        registry.put(newJobOpeningEventProducer.getEventType(), newJobOpeningEventProducer);

        JobOpeningDeletedEventProducer jobOpeningDeletedEventProducer = new JobOpeningDeletedEventProducer(config, eventConfigProperties);
        registry.put(jobOpeningDeletedEventProducer.getEventType(), jobOpeningDeletedEventProducer);

        JobOpeningUpdatedEventProducer jobOpeningUpdatedEventProducer = new JobOpeningUpdatedEventProducer(config, eventConfigProperties);
        registry.put(jobOpeningUpdatedEventProducer.getEventType(), jobOpeningUpdatedEventProducer);
    }

    public JobOpeningEventProducer getProducer(Class classType) {
        return registry.get(classType);

    }

}
