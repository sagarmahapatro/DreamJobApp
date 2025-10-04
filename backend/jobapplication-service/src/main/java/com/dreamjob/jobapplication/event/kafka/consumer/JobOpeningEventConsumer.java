package com.dreamjob.jobapplication.event.kafka.consumer;

import com.dreamjob.event.EventConfigProperties;
import com.dreamjob.jobapplication.event.jobopening.JobOpeningCreatEvent;
import com.dreamjob.jobapplication.event.jobopening.JobOpeningDeleteEvent;
import com.dreamjob.jobapplication.event.jobopening.JobOpeningUpdateEvent;
import com.dreamjob.jobapplication.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JobOpeningEventConsumer {
    public static final String JOBOPENING_CREATED = "jobopening-created";
    public static final String JOBOPENING_UPDATTED = "jobopening-updated";
    public static final String JOBOPENING_DELETED = "jobopening-deleted";

    private final JobApplicationService jobApplicationService;

    @Autowired
    public JobOpeningEventConsumer(JobApplicationService jobApplicationService, EventConfigProperties eventConfigProperties) {
        this.jobApplicationService = jobApplicationService;
    }


    @KafkaListener(
            topics = {
                    "#{@eventConfigProperties.jobapplication.consumerTopics[T(com.dreamjob.jobapplication.event.kafka.consumer.JobOpeningEventConsumer).JOBOPENING_CREATED]}",
                    "#{@eventConfigProperties.jobapplication.consumerTopics[T(com.dreamjob.jobapplication.event.kafka.consumer.JobOpeningEventConsumer).JOBOPENING_UPDATTED]}",
                    "#{@eventConfigProperties.jobapplication.consumerTopics[T(com.dreamjob.jobapplication.event.kafka.consumer.JobOpeningEventConsumer).JOBOPENING_DELETED]}"
            },
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(Object event) {
        if (event instanceof JobOpeningCreatEvent created) {
            jobApplicationService.processJobOpeningCreatedEvent(created);
        } else if (event instanceof JobOpeningUpdateEvent updated) {
            jobApplicationService.processJobOpeningUpdateEvent(updated);
        } else if (event instanceof JobOpeningDeleteEvent deleted) {
            jobApplicationService.processJobOpeningDeleteEvent(deleted);
        }
    }
}
