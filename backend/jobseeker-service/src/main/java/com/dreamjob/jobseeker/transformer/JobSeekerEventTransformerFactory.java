package com.dreamjob.jobseeker.transformer;

import com.dreamjob.jobseeker.event.JobSeekerEvent;
import org.springframework.stereotype.Component;

@Component
public class JobSeekerEventTransformerFactory {

    public JobSeekerEventTransformerFactory() {}

    public <T extends JobSeekerEvent>  JobSeekerEventTransformer<T>  create(Class<T> tClass) {
        JobSeekerEventTransformer<T> transformer = new JobSeekerEventTransformer<>(tClass);
        return transformer;
    }
}
