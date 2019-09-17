package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.QaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QaUserRepository extends JpaRepository<QaUserEntity, Integer> {
    Optional<QaUserEntity> findByUserName(String userName);
}
