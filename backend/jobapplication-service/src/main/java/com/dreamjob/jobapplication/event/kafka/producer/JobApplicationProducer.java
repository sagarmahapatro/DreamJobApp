package com.dreamjob.jobapplication.event.kafka.producer;

import com.dreamjob.event.EventConfigProperties;
import com.dreamjob.jobapplication.config.KafkaProducerConfig;
import com.dreamjob.jobapplication.event.JobApplicationStatusChangedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationProducer {
    private final String JOBAPPLICATION_STATUS_CHANGED = "jobapplication-statuschanged";
    protected final EventConfigProperties eventConfigProperties;
    protected final KafkaProducerConfig config;

    public JobApplicationProducer(EventConfigProperties eventConfigProperties, KafkaProducerConfig config) {
        this.eventConfigProperties = eventConfigProperties;
        this.config = config;
    }

    public void sendJobApplicationCreatedEvent(JobApplicationStatusChangedEvent jobApplicationStatusChangedEvent) {
        String topic = eventConfigProperties.getJobapplication().getProducerTopics().get(JOBAPPLICATION_STATUS_CHANGED);
        KafkaTemplate<String, JobApplicationStatusChangedEvent> kafkaTemplate = config.createTemplate(JobApplicationStatusChangedEvent.class);
        kafkaTemplate.send(topic, jobApplicationStatusChangedEvent.getApplicationId(), jobApplicationStatusChangedEvent);
        System.out.println("Sent JOBAPPLICATION_STATUS_CHANGED event for ApplicationId: " + jobApplicationStatusChangedEvent.getApplicationId());
    }
}
