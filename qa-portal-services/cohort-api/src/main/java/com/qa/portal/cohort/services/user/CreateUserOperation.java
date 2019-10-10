package com.qa.portal.cohort.services.user;

import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.exception.QaMultiStepCommitContext;
import com.qa.portal.common.exception.QaPortalMultiStepCommitException;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import com.qa.portal.common.persistence.repository.QaUserRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.common.dto.QaUserDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CreateUserOperation {

    private final Logger LOGGER = LoggerFactory.getLogger(CreateUserOperation.class);

    private QaUserRepository userRepository;

    private UserFactory userFactory;

    private BaseMapper baseMapper;

    public CreateUserOperation(QaUserRepository userRepository,
                               UserFactory userFactory,
                               BaseMapper baseMapper) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.baseMapper = baseMapper;
    }

    // Creation of user in its own transaction. We are also creating a user on keycloak as part of a separate transaction,
    // so we want to be able to identify when there are any issues creating these resources, so we can carry out
    // retry and potentially offline fixes to the data
    @Transactional
    public QaUserDetailsDto createUserDetails(QaUserDetailsDto userDetailsDto) {
        try {
            return getUser(userDetailsDto)
                    .map(u -> getExistingUser(userDetailsDto, u))
                    .orElseGet(() -> createNewUser(userDetailsDto));
        }
        catch (Exception e) {
            throw new QaPortalMultiStepCommitException(new QaMultiStepCommitContext(this.getClass().getName(),
                    userDetailsDto,
                    QaUserDetailsDto.class,
                    1), e.getMessage());
        }
    }

    private QaUserDetailsDto getExistingUser(QaUserDetailsDto userDetailsDto, QaUserEntity userEntity) {
        QaUserDto userDto = baseMapper.mapToQaUserDto(userEntity);
        userDetailsDto.setUser(userDto);
        return userDetailsDto;
    }

    private QaUserDetailsDto createNewUser(QaUserDetailsDto userDetailsDto) {
        return userFactory.createUser(userDetailsDto);
    }

    private Optional<QaUserEntity> getUser(QaUserDetailsDto userDetailsDto) {
        return userRepository.findByUserName(userDetailsDto.getUser().getUserName());
    }
}
