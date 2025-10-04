package com.dreamjob.jobseeker.event.kafka.producer;


import com.dreamjob.event.EventConfigProperties;
import com.dreamjob.jobseeker.config.KafkaProducerConfig;
import com.dreamjob.jobseeker.event.JobSeekerCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;

public class JobSeekerCreatedEventProducer extends AbstractJobSeekerEventProducer<JobSeekerCreatedEvent> {
    private final String  JOBSEEKER_CREATED = "jobseeker-created";

    public JobSeekerCreatedEventProducer(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        super(config, eventConfigProperties);
    }

    @Override
    public Class<JobSeekerCreatedEvent> getEventType() {
        return JobSeekerCreatedEvent.class;
    }

    @Override
    public void produce(JobSeekerCreatedEvent jobSeekerEvent) {
        sendJobSeekerCreatedEvent(jobSeekerEvent);
    }

    public void sendJobSeekerCreatedEvent(JobSeekerCreatedEvent jobSeekerCreatedEvent) {
        String topic = eventConfigProperties.getJobseeker().getProducerTopics().get(JOBSEEKER_CREATED);
        KafkaTemplate<String, JobSeekerCreatedEvent> kafkaTemplate = config.createTemplate(JobSeekerCreatedEvent.class);
        kafkaTemplate.send(topic, jobSeekerCreatedEvent.getJobSeekerId(), jobSeekerCreatedEvent);
        System.out.println("Sent JOBSEEKER_CREATED event for JobSeekerId: " + jobSeekerCreatedEvent.getJobSeekerId());
    }
}
