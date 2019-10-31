package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.CourseEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CohortCourseRepository extends JpaRepository<CohortCourseEntity, Integer> {

    List<CohortCourseEntity> findByTrainer(TrainerEntity trainer);

    List<CohortCourseEntity> findByCourse(CourseEntity course);

    List<CohortCourseEntity> findByEndDate(Date endDate);
}
