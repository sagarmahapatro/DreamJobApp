package com.dreamjob.Jobopening.repository;

import com.dreamjob.Jobopening.model.JobOpening;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;


import java.util.UUID;

public class JobOpeningRepositoryUpdateImpl implements JobOpeningRepositoryUpdate {


    private final MongoTemplate mongoTemplate;

    public JobOpeningRepositoryUpdateImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void updateSkill(UUID jobId, String skillName, int newRating) {
        Criteria criteria = Criteria.where("_id").is(jobId)
                .and("requiredSkills").elemMatch(Criteria.where("name").is(skillName));
        UpdateDefinition update = new Update().set("requiredSkills.$.rating", newRating);
        Query query = new Query(criteria);
        mongoTemplate.updateFirst( query, update, JobOpening.class);
    }
}
