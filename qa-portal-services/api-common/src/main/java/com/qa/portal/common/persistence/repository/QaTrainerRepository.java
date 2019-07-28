package com.qa.portal.common.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.portal.common.persistence.entity.TrainerEntity;

@Repository
public interface QaTrainerRepository extends JpaRepository<TrainerEntity, Integer> {
	Set<TrainerEntity> findByUserName(String userName);
}
