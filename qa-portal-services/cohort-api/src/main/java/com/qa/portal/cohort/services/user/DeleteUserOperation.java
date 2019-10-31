package com.qa.portal.cohort.services.user;

import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.common.exception.QaMultiStepCommitContext;
import com.qa.portal.common.exception.QaPortalMultiStepCommitException;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import com.qa.portal.common.persistence.repository.QaUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class DeleteUserOperation {

    private final Logger LOGGER = LoggerFactory.getLogger(DeleteUserOperation.class);

    private QaUserRepository userRepository;

    public DeleteUserOperation(QaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void deleteUsers(List<QaUserDetailsDto> users) {
        try {
            users.stream()
                    .filter(u -> u != null)
                    .map(u -> getUserEntity(u))
                    .forEach(u -> u.ifPresent((userEntity) -> userRepository.delete(userEntity)));
        }
        catch (Exception e) {
            throw new QaPortalMultiStepCommitException(new QaMultiStepCommitContext(this.getClass().getName(),
                    users,
                    List.class,
                    1), e.getMessage());
        }
    }

    private Optional<QaUserEntity> getUserEntity(QaUserDetailsDto userDetailsDto) {
        return  userRepository.findByUserName(userDetailsDto.getUser().getUserName());
    }
}
