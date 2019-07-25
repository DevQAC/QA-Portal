package com.qa.portal.reflection.persistence.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.portal.common.persistence.entity.QaTraineeEntity;
import com.qa.portal.common.persistence.entity.QaTrainerEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;

@Repository
public interface ReflectionRepository extends JpaRepository<ReflectionEntity, Integer> {

	Optional<ReflectionEntity> findByResponder(QaTraineeEntity trainee);

	Optional<ReflectionEntity> findByReviewerAndFormDate(TrainerEntity trainer, LocalDate date);

}
