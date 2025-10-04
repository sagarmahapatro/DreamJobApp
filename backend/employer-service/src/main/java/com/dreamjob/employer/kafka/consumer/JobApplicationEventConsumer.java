package com.dreamjob.employer.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationEventConsumer {

    @KafkaListener(topics = "job-application", groupId = "employer-service-1")
    public void consume(Object event) {

    }
}
