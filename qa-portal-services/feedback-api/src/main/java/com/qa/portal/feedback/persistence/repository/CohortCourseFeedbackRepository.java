package com.qa.portal.feedback.persistence.repository;

import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.portal.feedback.persistence.entity.CohortCourseFeedbackEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CohortCourseFeedbackRepository extends JpaRepository<CohortCourseFeedbackEntity, Integer> {

	Optional<CohortCourseFeedbackEntity> findByCohortCourse(CohortCourseEntity cohortCourseEntity);
}
