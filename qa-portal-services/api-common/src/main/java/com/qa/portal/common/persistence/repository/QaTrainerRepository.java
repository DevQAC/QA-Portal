package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QaTrainerRepository extends JpaRepository<TrainerEntity, Integer> {
    Optional<TrainerEntity> findByUserName(String userName);
}
