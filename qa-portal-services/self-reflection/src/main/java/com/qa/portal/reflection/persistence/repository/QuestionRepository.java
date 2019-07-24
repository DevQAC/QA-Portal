package com.qa.portal.reflection.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.portal.reflection.persistence.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

}
