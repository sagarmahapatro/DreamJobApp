package com.dreamjob.Jobopening.event.kafka.producer;

import com.dreamjob.Jobopening.config.KafkaProducerConfig;
import com.dreamjob.Jobopening.event.JobOpeningUpdatedEvent;
import com.dreamjob.event.EventConfigProperties;
import org.springframework.kafka.core.KafkaTemplate;

public class JobOpeningUpdatedEventProducer extends AbstractJobOpeningEventProducer<JobOpeningUpdatedEvent> {
    private final String  JOBOPENING_UPDATED = "jobopening-updated";

    public JobOpeningUpdatedEventProducer(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        super(config, eventConfigProperties);
    }

    @Override
    public Class<JobOpeningUpdatedEvent> getEventType() {
        return JobOpeningUpdatedEvent.class;
    }

    @Override
    public void produce(JobOpeningUpdatedEvent jobSeekerEvent) {

        sendJobSeekerUpdatedEvent(jobSeekerEvent);
    }
    public void sendJobSeekerUpdatedEvent(JobOpeningUpdatedEvent jobSeekerUpdatedEvent) {
        String topic = eventConfigProperties.getJobopening().getProducerTopics().get(JOBOPENING_UPDATED);
        KafkaTemplate<String, JobOpeningUpdatedEvent> kafkaTemplate = config.createTemplate(JobOpeningUpdatedEvent.class);
        kafkaTemplate.send(topic, jobSeekerUpdatedEvent.getJobOpeningId(), jobSeekerUpdatedEvent);
        System.out.println("Sent JOBOPENING_UPDATED event for JobSeekerId: " + jobSeekerUpdatedEvent.getJobOpeningId());
    }
}
