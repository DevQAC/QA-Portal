package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QaTraineeRepository extends JpaRepository<TraineeEntity, Integer> {
	Optional<TraineeEntity> findByUserName(String userName);
}
