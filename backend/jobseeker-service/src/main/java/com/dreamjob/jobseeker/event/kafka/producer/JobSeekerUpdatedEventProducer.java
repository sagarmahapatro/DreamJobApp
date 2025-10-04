package com.dreamjob.jobseeker.event.kafka.producer;

import com.dreamjob.event.EventConfigProperties;
import com.dreamjob.jobseeker.config.KafkaProducerConfig;
import com.dreamjob.jobseeker.event.JobSeekerUpdatedEvent;
import org.springframework.kafka.core.KafkaTemplate;

public class JobSeekerUpdatedEventProducer  extends AbstractJobSeekerEventProducer<JobSeekerUpdatedEvent>{
    private final String  JOBSEEKER_UPDATED = "jobseeker-updated";

    public JobSeekerUpdatedEventProducer(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        super(config, eventConfigProperties);
    }

    @Override
    public Class<JobSeekerUpdatedEvent> getEventType() {
        return JobSeekerUpdatedEvent.class;
    }

    @Override
    public void produce(JobSeekerUpdatedEvent jobSeekerEvent) {
        sendJobSeekerUpdatedEvent(jobSeekerEvent);
    }
    public void sendJobSeekerUpdatedEvent(JobSeekerUpdatedEvent jobSeekerUpdatedEvent) {
        String topic = eventConfigProperties.getJobseeker().getProducerTopics().get(JOBSEEKER_UPDATED);
        KafkaTemplate<String, JobSeekerUpdatedEvent> kafkaTemplate = config.createTemplate(JobSeekerUpdatedEvent.class);
        kafkaTemplate.send(topic, jobSeekerUpdatedEvent.getJobSeekerId(), jobSeekerUpdatedEvent);
        System.out.println("Sent JOBSEEKER_UPDATED event for JobSeekerId: " + jobSeekerUpdatedEvent.getJobSeekerId());
    }
}
