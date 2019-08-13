package com.qa.portal.feedback.persistence.repository;

import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CohortCourseEvaluationRepository extends JpaRepository<CohortCourseEvaluationEntity, Integer> {

    List<CohortCourseEvaluationEntity> findByTrainee(TraineeEntity trainee);
}
