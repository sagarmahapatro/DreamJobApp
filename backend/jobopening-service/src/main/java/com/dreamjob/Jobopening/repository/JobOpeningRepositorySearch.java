package com.dreamjob.Jobopening.repository;

import com.dreamjob.Jobopening.model.JobOpening;
import com.dreamjob.Jobopening.model.SkillName;

import java.util.List;

public interface JobOpeningRepositorySearch {

    List<JobOpening> search(
            List<SkillName> skills,
            String location,
            String status
    );
}
