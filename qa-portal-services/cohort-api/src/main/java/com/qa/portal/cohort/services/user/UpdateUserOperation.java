package com.qa.portal.cohort.services.user;

import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.common.exception.QaMultiStepCommitContext;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.exception.QaPortalMultiStepCommitException;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import com.qa.portal.common.persistence.repository.QaUserRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UpdateUserOperation {

    private QaUserRepository userRepository;

    private BaseMapper baseMapper;

    public UpdateUserOperation(QaUserRepository userRepository, BaseMapper baseMapper) {
        this.userRepository = userRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public QaUserDetailsDto updateUserDetails(QaUserDetailsDto userDetailsDto) {
        try {
            return getUser(userDetailsDto).map(u -> updateExistingUser(userDetailsDto))
                    .orElseThrow(() -> new QaPortalBusinessException("User being updated does not exist"));
        }
        catch (Exception e) {
            throw new QaPortalMultiStepCommitException(new QaMultiStepCommitContext(this.getClass().getName(),
                    userDetailsDto,
                    QaUserDetailsDto.class,
                    1), e.getMessage());
        }
    }

    private QaUserDetailsDto updateExistingUser(QaUserDetailsDto userDetailsDto) {
        return getUser(userDetailsDto)
                .map(u -> updateExistingUser(u, userDetailsDto))
                .orElseThrow(() -> new QaPortalBusinessException("No user found for supplied username"));
    }

    private QaUserDetailsDto updateExistingUser(QaUserEntity existingUserEntity, QaUserDetailsDto userDetailsDto) {
        existingUserEntity.setFirstName(userDetailsDto.getUser().getFirstName());
        existingUserEntity.setLastName(userDetailsDto.getUser().getLastName());
        userDetailsDto.setUser(baseMapper.mapToQaUserDto(existingUserEntity));
        return userDetailsDto;
    }

    private Optional<QaUserEntity> getUser(QaUserDetailsDto userDetailsDto) {
        return userRepository.findByUserName(userDetailsDto.getUser().getUserName());
    }
}
