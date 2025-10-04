package com.dreamjob.Jobopening.transformer;

import com.dreamjob.Jobopening.event.JobOpeningEvent;
import com.dreamjob.Jobopening.model.JobOpening;
import com.dreamjob.transformer.Transformer;
import org.springframework.stereotype.Component;

import java.util.Optional;


public class JobOpeningEventTransformer<T extends JobOpeningEvent> implements Transformer<JobOpening, Optional<T> > {

    private Class<T> clazz;

    public JobOpeningEventTransformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T createInstance() throws Exception {
        return clazz.getDeclaredConstructor().newInstance();
    }

    @Override
    public Optional<T> transform(JobOpening source) {
        Optional<T> returnValue = Optional.empty();
        try {
            T t = createInstance();
            JobOpeningEvent event = process(source);
            t.copy(event);
            return returnValue.of(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JobOpeningEvent  process( JobOpening source) {
        JobOpeningEvent event = new JobOpeningEvent();
        event.setJobOpeningId(source.getId().toString());
        event.setDescription(source.getDescription());
        event.setTitle(source.getTitle());
        event.setEmployerId(source.getEmployerId().toString());
        return event;
    }
}
