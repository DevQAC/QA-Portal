package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.TrainingAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QaTrainingAdminRepository extends JpaRepository<TrainingAdminEntity, Integer> {
    List<TrainingAdminEntity> findByUserName(String userName);
}
