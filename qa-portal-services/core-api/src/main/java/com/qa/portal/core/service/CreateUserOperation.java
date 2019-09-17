package com.qa.portal.core.service;

import com.qa.portal.core.dto.QaUserDetailsDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import com.qa.portal.common.persistence.repository.QaUserRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateUserOperation {

    private QaUserRepository userRepository;

    private BaseMapper baseMapper;

    public CreateUserOperation(QaUserRepository userRepository,
                               BaseMapper baseMapper) {
        this.userRepository = userRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public QaUserDetailsDto createUserDetails(QaUserDetailsDto userDetailsDto) {
        validateUser(userDetailsDto);
        QaUserEntity userEntity = baseMapper.mapToQaUserEntity(userDetailsDto.getUser());
        userRepository.save(userEntity);
        userDetailsDto.setUser(baseMapper.mapToQaUserDto(userEntity));
        return userDetailsDto;
    }

    private void validateUser(QaUserDetailsDto userDetailsDto) {
        if (userExists(userDetailsDto)) {
            throw new QaPortalBusinessException("User already exists for supplied username");
        }
    }

    private boolean userExists(QaUserDetailsDto userDetailsDto) {
        return userRepository.findByUserName(userDetailsDto.getUser().getUserName())
                .map(u -> true)
                .orElseGet(() -> false);
    }
}
