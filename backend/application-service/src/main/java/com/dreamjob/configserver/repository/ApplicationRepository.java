package  com.dreamjob.configserver.repository;

import com.dreamjob.configserver.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, UUID> { }
