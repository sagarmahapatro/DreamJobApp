package  com.dreamjob.jobapplication.repository;

import com.dreamjob.jobapplication.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobApplicationRepository extends MongoRepository<JobApplication, UUID> { }
