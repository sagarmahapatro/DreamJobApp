package com.dreamjob.Jobopening.event.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JobOpeningEventConsumer {

    public JobOpeningEventConsumer() {

    }

    @KafkaListener(
            topics = "#{@eventConfigProperties.jobseeker.consumerTopics['jobapplication-statuschanged']}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(Object event) {
//topics = "#{@messagingProperties.choreography['job-opening']['consumed']['application-service']}",
    }
}
