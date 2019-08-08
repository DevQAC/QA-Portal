package com.qa.portal.common.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.portal.common.persistence.entity.TraineeEntity;

@Repository
public interface QaTraineeRepository extends JpaRepository<TraineeEntity, Integer> {
    Optional<TraineeEntity> findByUserName(String userName);
}
