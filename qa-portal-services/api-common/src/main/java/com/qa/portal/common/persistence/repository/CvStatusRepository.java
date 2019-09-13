package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.CvStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvStatusRepository extends JpaRepository<CvStatusEntity, Integer> {
}
