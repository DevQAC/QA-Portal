package com.qa.portal.reflection.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;

public interface ReflectionQuestionRepository extends JpaRepository<ReflectionQuestionEntity, Integer> {

}
