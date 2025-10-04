package com.dreamjob.jobapplication.repository;


import com.dreamjob.jobapplication.model.JobOpening;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobOpeningRepository extends MongoRepository<JobOpening, UUID> {
    Optional<JobOpening> findJobOpeningByJobOpeningId(String jobOpeningId);
    Optional<JobOpening> findJobOpeningByTitle(String title);
    List<JobOpening> findJobOpeningByEmployerId(String employerId);
}




