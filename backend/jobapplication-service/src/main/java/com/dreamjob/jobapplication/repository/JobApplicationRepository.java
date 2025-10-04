package  com.dreamjob.jobapplication.repository;

import com.dreamjob.jobapplication.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobApplicationRepository extends MongoRepository<JobApplication, UUID> {

    List<JobApplication> findByJobSeekerId(UUID jobSeekerId);
    List<JobApplication> findByJobOpeningId(UUID jobOpeningId);
    Optional<JobApplication> findByApplicationId(String applicationId);

    // Delete JobApplications by JobSeekerId
    void deleteByJobSeekerId(UUID jobSeekerId);

    // Delete JobApplications by JobOpeningId
    void deleteByJobOpeningId(UUID jobOpeningId);
}
