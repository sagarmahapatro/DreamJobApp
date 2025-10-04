package com.dreamjob.Jobopening.repository;

import com.dreamjob.Jobopening.model.JobOpening;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobOpeningRepository extends MongoRepository<JobOpening, UUID> , JobOpeningRepositorySearch, JobOpeningRepositoryUpdate {
    List<JobOpening> findByEmployerId(Long employerId);
    List<JobOpening> findByStatus(String status);

    // Custom query: must match all required skills
    @Query("{ 'requiredSkills.name': { $all: ?0 } }")
    List<JobOpening> findByMatchingSkills(List<String> mandatorySkills);

    @Query("{ 'requiredSkills.name': { $all: ?0 }, 'location': ?1, 'status': ?2 }")
    List<JobOpening> searchJobOpenings(List<String> skills, String location, String status);
}
