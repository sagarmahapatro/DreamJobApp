package com.dreamjob.Jobopening.repository;

import com.dreamjob.Jobopening.model.JobOpening;
import com.dreamjob.Jobopening.model.RequiredSkill;
import com.dreamjob.Jobopening.model.SkillName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JobOpeningRepositorySearchImpl implements  JobOpeningRepositorySearch {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public JobOpeningRepositorySearchImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<JobOpening> search(
            List<SkillName> skills, String location, String status) {

        List<Criteria> criteriaList = new ArrayList<>();

        if (skills != null && !skills.isEmpty()) {
            List<String> skillNames = skills.stream().map(Enum::name).toList();
            criteriaList.add(Criteria.where("requiredSkills.name").all(skillNames));
        }
        if (location != null) {
            criteriaList.add(Criteria.where("location").is(location));
        }
        if (status != null) {
            criteriaList.add(Criteria.where("status").is(status));
        }

        Query query = new Query(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        return mongoTemplate.find(query, JobOpening.class);
    }

    public List<JobOpening> searchByMandatorySkillsWithExperience(List<RequiredSkill> skills) {
        List<Criteria> criteriaList = new ArrayList<>();
        for (RequiredSkill skill : skills) {
            criteriaList.add(Criteria.where("requiredSkills")
                    .elemMatch(Criteria.where("name").is(skill.getName())
                            .and("yearsOfExperience").gte(skill.getMinimumYearsOfExperienceRequired())
                            .and("mandatory").gte(skill.getMandatory())));
        }
        Query query = new Query(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        return mongoTemplate.find(query, JobOpening.class);
    }
}
