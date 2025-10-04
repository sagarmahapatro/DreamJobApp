package com.dreamjob.jobseeker.event.kafka.producer;

import com.dreamjob.event.EventConfigProperties;
import com.dreamjob.jobseeker.config.KafkaProducerConfig;
import com.dreamjob.jobseeker.event.JobSeekerEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JobSeekerEventProducerFactory {

    private final EventConfigProperties eventConfigProperties;
    private final KafkaProducerConfig config;

    private Map<Class, JobSeekerEventProducer> registry;

    public JobSeekerEventProducerFactory(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        this.config = config;
        this.eventConfigProperties = eventConfigProperties;
        registry = new HashMap<>();
        registerJobSeekerEventProducer();
    }

    private void registerJobSeekerEventProducer() {
        JobSeekerCreatedEventProducer jobSeekerCreatedEventProducer = new JobSeekerCreatedEventProducer(config, eventConfigProperties);
        registry.put(jobSeekerCreatedEventProducer.getEventType(), jobSeekerCreatedEventProducer);

        JobSeekerDeletedEventProducer jobSeekerDeletedEventProducer = new JobSeekerDeletedEventProducer(config, eventConfigProperties);
        registry.put(jobSeekerDeletedEventProducer.getEventType(), jobSeekerDeletedEventProducer);

        JobSeekerUpdatedEventProducer jobSeekerUpdatedEventProducer = new JobSeekerUpdatedEventProducer(config, eventConfigProperties);
        registry.put(jobSeekerUpdatedEventProducer.getEventType(), jobSeekerUpdatedEventProducer);
    }

    public JobSeekerEventProducer getProducer(Class classType) {
        return registry.get(classType);

    }

}
