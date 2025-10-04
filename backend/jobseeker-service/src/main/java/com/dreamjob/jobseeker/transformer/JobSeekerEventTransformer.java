package com.dreamjob.jobseeker.transformer;

import com.dreamjob.jobseeker.event.JobSeekerEvent;
import com.dreamjob.jobseeker.model.JobSeeker;
import com.dreamjob.transformer.Transformer;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class JobSeekerEventTransformer<T extends JobSeekerEvent> implements Transformer<JobSeeker, Optional<T> > {

    private Class<T> clazz;

    public JobSeekerEventTransformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T createInstance() throws Exception {
        return clazz.getDeclaredConstructor().newInstance();
    }

    @Override
    public Optional<T> transform(JobSeeker source) {
        Optional<T> returnValue = Optional.empty();
        try {
            T t = createInstance();
            JobSeekerEvent event = process(source);
            t.copy(event);
            return returnValue.of(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JobSeekerEvent  process( JobSeeker source) {
        JobSeekerEvent event = new JobSeekerEvent();
        event.setJobSeekerId(String.valueOf(source.getId()));
        event.setEmail(source.getProfile().getEmail());
        event.setName(source.getProfile().getFullName());
        event.setResumeSummery(source.getResumeSummery());
        return event;
    }
}
