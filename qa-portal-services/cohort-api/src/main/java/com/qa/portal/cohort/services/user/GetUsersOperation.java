package com.qa.portal.cohort.services.user;

import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.QaUserRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetUsersOperation {

    private QaUserRepository userRepository;

    private BaseMapper baseMapper;

    public GetUsersOperation(QaUserRepository userRepository,
                             BaseMapper baseMapper) {
        this.userRepository = userRepository;
        this.baseMapper = baseMapper;
    }

    public QaUserDto getUser(Integer id) {
        return userRepository.findById(id)
                .map(ue -> baseMapper.mapToQaUserDto(ue))
                .orElseThrow(() -> new QaPortalBusinessException("No user found for supplied id"));
    }

    public List<QaUserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(ue -> baseMapper.mapToQaUserDto(ue))
                .collect(Collectors.toList());
    }
}
