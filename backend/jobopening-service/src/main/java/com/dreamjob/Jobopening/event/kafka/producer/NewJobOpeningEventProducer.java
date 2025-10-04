package com.dreamjob.Jobopening.event.kafka.producer;


import com.dreamjob.Jobopening.config.KafkaProducerConfig;
import com.dreamjob.Jobopening.event.NewJobOpeningEvent;
import com.dreamjob.event.EventConfigProperties;
import org.springframework.kafka.core.KafkaTemplate;

public class NewJobOpeningEventProducer extends AbstractJobOpeningEventProducer<NewJobOpeningEvent> {
    private final String  JOBOPENING_CREATED = "jobopening-created";

    public NewJobOpeningEventProducer(KafkaProducerConfig config, EventConfigProperties eventConfigProperties) {
        super(config, eventConfigProperties);
    }

    @Override
    public Class<NewJobOpeningEvent> getEventType() {
        return NewJobOpeningEvent.class;
    }

    @Override
    public void produce(NewJobOpeningEvent jobSeekerEvent) {
        sendJobSeekerCreatedEvent(jobSeekerEvent);
    }

    public void sendJobSeekerCreatedEvent(NewJobOpeningEvent newJobOpeningEvent) {
        String topic = eventConfigProperties.getJobopening().getProducerTopics().get(JOBOPENING_CREATED);
        KafkaTemplate<String, NewJobOpeningEvent> kafkaTemplate = config.createTemplate(NewJobOpeningEvent.class);
        kafkaTemplate.send(topic, newJobOpeningEvent.getJobOpeningId(), newJobOpeningEvent);
        System.out.println("Sent JOBOPENING_CREATED event for getJobOpeningId: " + newJobOpeningEvent.getJobOpeningId());
    }
}
