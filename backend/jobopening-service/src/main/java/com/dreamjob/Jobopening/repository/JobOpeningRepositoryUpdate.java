package com.dreamjob.Jobopening.repository;

import java.util.UUID;

public interface JobOpeningRepositoryUpdate {
    void updateSkill(UUID jobId, String skillName, int newRating);
}
