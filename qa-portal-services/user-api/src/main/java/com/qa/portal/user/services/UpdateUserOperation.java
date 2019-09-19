package com.qa.portal.user.services;

import com.qa.portal.common.persistence.repository.QaUserRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.common.dto.QaUserDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserOperation {

    private QaUserRepository qaUserRepository;

    private BaseMapper baseMapper;

    public UpdateUserOperation(QaUserRepository qaUserRepository, BaseMapper baseMapper) {
        this.qaUserRepository = qaUserRepository;
        this.baseMapper = baseMapper;
    }

    public QaUserDetailsDto updateUserDetails(QaUserDetailsDto userDetailsDto) {
        return userDetailsDto;
    }
}
