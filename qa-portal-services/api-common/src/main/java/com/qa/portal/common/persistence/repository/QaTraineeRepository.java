package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QaTraineeRepository extends JpaRepository<TraineeEntity, Integer> {
	List<TraineeEntity> findByUserName(String userName);
}
