package com.qa.portal.user.services;

import com.qa.portal.common.exception.QaMultiStepCommitContext;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.exception.QaPortalMultiStepCommitException;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import com.qa.portal.common.persistence.repository.QaUserRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.user.dto.QaUserDetailsDto;
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

    // Creation of user in its own transaction. We are also creating a user on keycloak as part or a separate transaction,
    // so we want to be able to identify when there are any issues creating these resources, so we can carry out
    // retry and potentially offline fixes to the data
    @Transactional
    public QaUserDetailsDto createUserDetails(QaUserDetailsDto userDetailsDto) {
        try {
            validateUser(userDetailsDto);
            QaUserEntity userEntity = baseMapper.mapToQaUserEntity(userDetailsDto.getUser());
            userRepository.save(userEntity);
            userDetailsDto.setUser(baseMapper.mapToQaUserDto(userEntity));
            return userDetailsDto;
        }
        catch (Exception e) {
            throw new QaPortalMultiStepCommitException(new QaMultiStepCommitContext(this.getClass().getName(),
                    userDetailsDto,
                    QaUserDetailsDto.class,
                    1), e.getMessage());
        }
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
