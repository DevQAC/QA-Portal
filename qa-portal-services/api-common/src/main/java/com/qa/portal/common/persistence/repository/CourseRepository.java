package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    Optional<CourseEntity> findByCourseCode(String courseCode);
}
