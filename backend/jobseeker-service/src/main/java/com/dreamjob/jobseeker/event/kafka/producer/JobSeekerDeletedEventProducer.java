package com.dreamjob.jobseeker.event.kafka.producer;

import com.dreamjob.event.EventConfigProperties;
import com.dreamjob.jobseeker.config.KafkaProducerConfig;
import com.dreamjob.jobseeker.event.JobSeekerCreatedEvent;
import com.dreamjob.jobseeker.event.JobSeekerDeleteEvent;
import org.springframework.kafka.core.KafkaTemplate;

public class JobSeekerDeletedEventProducer  extends AbstractJobSeekerEventProducer<JobSeekerDeleteEvent>{
    private final String  JOBSEEKER_DELETED = "jobseeker-deleted";

    public JobSeekerDeletedEventProducer(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        super(config, eventConfigProperties);
    }

    @Override
    public Class<JobSeekerDeleteEvent> getEventType() {
        return JobSeekerDeleteEvent.class;
    }

    @Override
    public void produce(JobSeekerDeleteEvent jobSeekerEvent) {
        sendJobSeekerDeletedEvent(jobSeekerEvent);
    }

    public void sendJobSeekerDeletedEvent(JobSeekerDeleteEvent jobSeekerDeleteEvent) {
        String topic = eventConfigProperties.getJobseeker().getProducerTopics().get(JOBSEEKER_DELETED);
        KafkaTemplate<String, JobSeekerDeleteEvent> kafkaTemplate = config.createTemplate(JobSeekerDeleteEvent.class);
        kafkaTemplate.send(topic, jobSeekerDeleteEvent.getJobSeekerId(), jobSeekerDeleteEvent);
        System.out.println("Sent JOBSEEKER_DELETED event for JobSeekerId: " + jobSeekerDeleteEvent.getJobSeekerId());
    }
}
