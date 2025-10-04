package com.dreamjob.Jobopening.service;

import com.dreamjob.Jobopening.event.JobOpeningDeletedEvent;
import com.dreamjob.Jobopening.event.JobOpeningEvent;
import com.dreamjob.Jobopening.event.JobOpeningUpdatedEvent;
import com.dreamjob.Jobopening.event.NewJobOpeningEvent;
import com.dreamjob.Jobopening.event.kafka.producer.JobSeekerEventProducerFactory;
import com.dreamjob.Jobopening.model.JobOpening;
import com.dreamjob.Jobopening.model.SkillName;
import com.dreamjob.Jobopening.repository.JobOpeningRepository;
import com.dreamjob.Jobopening.transformer.JobOpeningEventTransformer;
import com.dreamjob.Jobopening.transformer.JobOpeningEventTransformerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobOpeningService {
    private final JobOpeningRepository repo;
    private final JobSeekerEventProducerFactory jobSeekerEventProducerFactory;
    private final JobOpeningEventTransformerFactory jobOpeningEventTransformerFactory;

    public JobOpeningService(JobOpeningRepository repo,
                             KafkaTemplate<String, String> kafka,
                             JobSeekerEventProducerFactory jobSeekerEventProducerFactory,
                             JobOpeningEventTransformerFactory jobOpeningEventTransformerFactory) {
        this.repo = repo;
        this.jobSeekerEventProducerFactory = jobSeekerEventProducerFactory;
        this.jobOpeningEventTransformerFactory = jobOpeningEventTransformerFactory;
    }

    public JobOpening register(JobOpening e) {
        JobOpening saved = repo.save(e);
        emitNewJobOpeningEvent(saved);
        return saved;
    }

    public JobOpening create(JobOpening jobOpening) {
        JobOpening saved = repo.save(jobOpening);
        emitNewJobOpeningEvent(saved);
        return saved;
    }

    public List<JobOpening> findAllJobOpening() {
        return repo.findAll();
    }

    public JobOpening update(UUID id, JobOpening updates) {
        JobOpening existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        existing.setTitle(updates.getTitle());
        existing.setDescription(updates.getDescription());
        existing.setLocation(updates.getLocation());
        existing.setStatus(updates.getStatus());
        existing.setRequiredSkills(updates.getRequiredSkills());
        JobOpening jobOpening = repo.save(existing);

        emitJobOpeningUpdatedEvent(jobOpening);

        return jobOpening;  // replaces doc in Mongo
    }

    public List<JobOpening> getJobOpeningPostedByEmployer(Long id) {
        return repo.findByEmployerId(id);
    }

    public JobOpening findJobOpening(UUID id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public JobOpening deleteJobOpening(UUID id) {
        JobOpening jobOpening = findJobOpening(id);
        repo.deleteById(jobOpening.getId());
        emitJobOpeningDeletedEvent(jobOpening);
        return jobOpening;
    }

    public List<JobOpening> search(List<SkillName> skills, String location, String status) {
        return repo.search(skills, location, status);
    }
    private void emitJobOpeningDeletedEvent(JobOpening existing) {
        emitJobOpeningEvent(existing, JobOpeningDeletedEvent.class);
    }

    private void emitNewJobOpeningEvent(JobOpening existing) {
        emitJobOpeningEvent(existing, NewJobOpeningEvent.class);
    }

    private void emitJobOpeningUpdatedEvent(JobOpening existing) {
        emitJobOpeningEvent(existing, JobOpeningUpdatedEvent.class);
    }

    private void emitJobOpeningEvent(JobOpening existing, Class<? extends JobOpeningEvent> clazz) {
        JobOpeningEventTransformer jobSeekerEventTransformer =  jobOpeningEventTransformerFactory.create(clazz);
        Optional<JobOpeningEvent> jobOpeningEventOpt  = jobSeekerEventTransformer.transform(existing);

        if(jobOpeningEventOpt.isPresent()) {
            JobOpeningEvent jobOpeningEvent = jobOpeningEventOpt.get();
            EventEmitter eventEmitter = jobSeekerEventProducerFactory.getProducer(clazz);
            eventEmitter.produce(jobOpeningEvent);
        } else {
            throw new RuntimeException("Failed to transform JobSeeker to JobSeekerDeleteEvent");
        }
    }
}
