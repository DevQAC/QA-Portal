package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.refdata.QaReferenceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QaCohortRepository extends JpaRepository<QaCohortEntity, Integer> {

    Optional<QaCohortEntity> findByName(String name);
}
