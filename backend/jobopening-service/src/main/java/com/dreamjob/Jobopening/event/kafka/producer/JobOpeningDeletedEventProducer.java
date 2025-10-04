package com.dreamjob.Jobopening.event.kafka.producer;

import com.dreamjob.Jobopening.config.KafkaProducerConfig;
import com.dreamjob.Jobopening.event.JobOpeningDeletedEvent;
import com.dreamjob.event.EventConfigProperties;
import org.springframework.kafka.core.KafkaTemplate;

public class JobOpeningDeletedEventProducer extends AbstractJobOpeningEventProducer<JobOpeningDeletedEvent> {
    private final String  JOBOPENING_DELETED = "jobopening-deleted";

    public JobOpeningDeletedEventProducer(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        super(config, eventConfigProperties);
    }

    @Override
    public Class<JobOpeningDeletedEvent> getEventType() {
        return JobOpeningDeletedEvent.class;
    }

    @Override
    public void produce(JobOpeningDeletedEvent jobOpeningDeletedEvent) {
        sendJobSeekerDeletedEvent(jobOpeningDeletedEvent);
    }

    public void sendJobSeekerDeletedEvent(JobOpeningDeletedEvent jobOpeningDeletedEvent) {
        String topic = eventConfigProperties.getJobopening().getProducerTopics().get(JOBOPENING_DELETED);
        KafkaTemplate<String, JobOpeningDeletedEvent> kafkaTemplate = config.createTemplate(JobOpeningDeletedEvent.class);
        kafkaTemplate.send(topic, jobOpeningDeletedEvent.getJobOpeningId(), jobOpeningDeletedEvent);
        System.out.println("Sent JOBOPENING_DELETED event for getJobOpeningId: " + jobOpeningDeletedEvent.getJobOpeningId());
    }
}
