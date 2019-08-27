package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.CourseTechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTechnologyRepository extends JpaRepository<CourseTechnologyEntity, Integer> {
}
