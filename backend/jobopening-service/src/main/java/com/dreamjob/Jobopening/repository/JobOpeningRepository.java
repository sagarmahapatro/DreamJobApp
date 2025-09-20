package com.dreamjob.Jobopening.repository;

import com.dreamjob.Jobopening.model.JobOpening;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobOpeningRepository extends MongoRepository<JobOpening, UUID>  { }
