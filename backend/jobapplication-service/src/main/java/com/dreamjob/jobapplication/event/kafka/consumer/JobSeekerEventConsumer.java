package com.dreamjob.jobapplication.event.kafka.consumer;

import com.dreamjob.jobapplication.event.jobseeker.JobSeekerCreatEvent;
import com.dreamjob.jobapplication.event.jobseeker.JobSeekerDeleteEvent;
import com.dreamjob.jobapplication.event.jobseeker.JobSeekerUpdateEvent;
import com.dreamjob.jobapplication.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class JobSeekerEventConsumer {
    public static final String JOBSEEKER_CREATED = "jobseeker-created";
    public static  final String JOBSEEKER_UPDATTED = "jobseeker-updated";
    public static  final String JOBSEEKER_DELETED = "jobseeker-deleted";

    private final JobApplicationService jobApplicationService;

    @Autowired
    public JobSeekerEventConsumer(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @KafkaListener(
            topics = {
                    "#{@eventConfigProperties.jobapplication.consumerTopics[T(com.dreamjob.jobapplication.event.kafka.consumer.JobSeekerEventConsumer).JOBSEEKER_CREATED]}",
                    "#{@eventConfigProperties.jobapplication.consumerTopics[T(com.dreamjob.jobapplication.event.kafka.consumer.JobSeekerEventConsumer).JOBSEEKER_UPDATTED]}",
                    "#{@eventConfigProperties.jobapplication.consumerTopics[T(com.dreamjob.jobapplication.event.kafka.consumer..JobSeekerEventConsumer).JOBSEEKER_DELETED]}"
            },
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(Object event) {
        if (event instanceof JobSeekerCreatEvent created) {
            jobApplicationService.processJobSeekerCreatEvent(created);
        } else if (event instanceof JobSeekerUpdateEvent updated) {
            jobApplicationService.processJobSeekerUpdateEvent(updated);
        } else if (event instanceof JobSeekerDeleteEvent deleted) {
            jobApplicationService.processJobSeekerDeleteEvent(deleted);
        }
    }
}
