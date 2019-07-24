package com.qa.portal.reflection.persistence.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.portal.reflection.persistence.entity.ReflectionEntity;

public interface ReflectionRepository extends JpaRepository<ReflectionEntity, Integer> {

	Optional<ReflectionEntity> findByReviewerAndDate(Integer reviewer_id, LocalDate date);

}
