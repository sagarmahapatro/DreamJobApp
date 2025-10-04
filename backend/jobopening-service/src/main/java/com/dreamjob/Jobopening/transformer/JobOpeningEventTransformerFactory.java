package com.dreamjob.Jobopening.transformer;

import com.dreamjob.Jobopening.event.JobOpeningEvent;
import org.springframework.stereotype.Component;

@Component
public class  JobOpeningEventTransformerFactory {

    public JobOpeningEventTransformerFactory() {}

    public <T extends JobOpeningEvent> JobOpeningEventTransformer<T> create(Class<T> tClass) {
        JobOpeningEventTransformer<T> transformer = new JobOpeningEventTransformer<>(tClass);
        return transformer;
    }
}
