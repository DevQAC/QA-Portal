package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CohortCourseRepository extends JpaRepository<CohortCourseEntity, Integer> {
}
