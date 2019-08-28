package com.qa.portal.feedback.persistence.repository;

import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CohortCourseEvaluationRepository extends JpaRepository<CohortCourseEvaluationEntity, Integer> {

    List<CohortCourseEvaluationEntity> findByTrainee(TraineeEntity trainee);

	List<CohortCourseEvaluationEntity> findByCohortCourse(CohortCourseEntity cohortCourse);

}
