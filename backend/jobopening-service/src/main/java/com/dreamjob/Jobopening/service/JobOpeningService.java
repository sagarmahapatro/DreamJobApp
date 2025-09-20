package com.dreamjob.Jobopening.service;

import com.dreamjob.Jobopening.model.JobOpening;
import com.dreamjob.Jobopening.repository.JobOpeningRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobOpeningService {
    private final JobOpeningRepository repo;
    private final KafkaTemplate<String, String> kafka;

    public JobOpeningService(JobOpeningRepository repo, KafkaTemplate<String, String> kafka) {
        this.repo = repo;
        this.kafka = kafka;
    }

    public JobOpening register(JobOpening e) {
        JobOpening saved = repo.save(e);
        kafka.send("job-openings", "New employer registered: " + saved.toString());
        return saved;
    }

    public JobOpening create(JobOpening jobOpening) {
        return null;
    }
}
