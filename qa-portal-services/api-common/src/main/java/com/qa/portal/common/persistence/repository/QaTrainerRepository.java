package com.qa.portal.common.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.portal.common.persistence.entity.QaTrainerEntity;

@Repository
public interface QaTrainerRepository extends JpaRepository<QaTrainerEntity, Integer> {

}
