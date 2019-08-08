package com.qa.portal.feedback.persistence.repository;

import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CohortCourseEvaluationRepository extends JpaRepository<CohortCourseEvaluationEntity, Integer> {
}
