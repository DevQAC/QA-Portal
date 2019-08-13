package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.FormTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormTypeRepository  extends JpaRepository<FormTypeEntity, Integer> {

    Optional<FormTypeEntity> findByFormName(String formName);
}
