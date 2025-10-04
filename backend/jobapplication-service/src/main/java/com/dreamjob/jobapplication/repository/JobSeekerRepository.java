package com.dreamjob.jobapplication.repository;

import com.dreamjob.jobapplication.model.JobOpening;
import com.dreamjob.jobapplication.model.JobSeeker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobSeekerRepository extends MongoRepository<JobSeeker, UUID> {
    Optional<JobSeeker> findJobSeekerByJobSeekerId(String jobSeekerId);
    Optional<JobSeeker> findJobSeekerByEmail(String email);
}
